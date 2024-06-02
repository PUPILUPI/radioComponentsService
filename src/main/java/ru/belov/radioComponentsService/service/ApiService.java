package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.belov.radioComponentsService.domain.apiFormat.*;
import ru.belov.radioComponentsService.domain.dto.SearchDetailDTORes;
import ru.belov.radioComponentsService.domain.dto.sql.FilterSellerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.mapper.SellerInfoMapper;
import ru.belov.radioComponentsService.repository.SellerInfoRepository;
import ru.belov.radioComponentsService.repository.SellersListRepository;
import ru.belov.radioComponentsService.specification.SellerInfoSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@Service
@RequiredArgsConstructor
public class ApiService {

    private final RestTemplate restTemplate;
    private final SellerInfoRepository sellerInfoRepository;
    private final SellersListRepository sellersListRepository;
    private final ChipFindParser parser;
    private final ResponseMapper responseMapper;
    private final SellerInfoMapper sellerInfoMapper;

    public List<SearchDetailDTORes> doRequests(Long userId,
                                               Boolean indFlag,
                                               Double rating,
                                               Boolean flagManufacturer,
                                               String city,
                                               Boolean favoriteFlag,
                                               Boolean blacklistFlag) {
        List<SellerInfo> filteredUrls = filterUrls(
                userId,
                indFlag,
                rating,
                flagManufacturer,
                city,
                favoriteFlag,
                blacklistFlag);
        return doRequests(filteredUrls);
    }

    private List<SellerInfo> filterUrls(Long userId,
                                        Boolean indFlag,
                                        Double rating,
                                        Boolean flagManufacturer,
                                        String city,
                                        Boolean favoriteFlag,
                                        Boolean blacklistFlag) {
        Specification<SellerInfo> spec = Specification.where(null);
        if (indFlag != null) {
            spec = spec.and(SellerInfoSpecification.hasIndFlag(indFlag));
        }
        if (flagManufacturer != null) {
            spec = spec.and(SellerInfoSpecification.hasflagManufacturer(flagManufacturer));
        }
        if (rating != null) {
            spec = spec.and(SellerInfoSpecification.hasRatingGreaterThanOrEqual(rating));
        }
        if (city != null) {
            spec = spec.and(SellerInfoSpecification.hasCity(city));
        }
        if(favoriteFlag != null && favoriteFlag) {
            List<Long> sellerIds = sellersListRepository.findByIdConsumerIdAndFavoriteFlag(userId, true)
                    .stream()
                    .map(sellerInfo -> sellerInfo.getId().getSellerId())
                    .toList();
            spec = spec.and(SellerInfoSpecification.hasIdsIn(sellerIds));
        } else if(blacklistFlag != null && blacklistFlag) {
            List<Long> sellerIds = sellersListRepository.findByIdConsumerIdAndFavoriteFlag(userId, false)
                    .stream()
                    .map(sellerInfo -> sellerInfo.getId().getSellerId())
                    .toList();
            spec = spec.and(SellerInfoSpecification.hasIdsNotIn(sellerIds));
        }
        return sellerInfoRepository.findAll(spec).stream()
                .filter(sellerInfo -> isNotEmpty(sellerInfo.getResponseFormat()))
                .filter(sellerInfo -> isNotEmpty(sellerInfo.getApiAddress()))
                .filter(SellerInfo::getAgreementFlag)
                .toList();
    }

    private List<SearchDetailDTORes> doRequests(List<SellerInfo> sellers) {
        CountDownLatch latch = new CountDownLatch(sellers.size());
        List<Future<String>> futures = new ArrayList<>();
        List<SearchDetailDTORes> results = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(sellers.size());
        for (SellerInfo info : sellers) {
            Future<String> future = executor.submit(() -> {
                try {
                    latch.countDown(); // Уменьшаем счетчик при старте потока
                    latch.await(); // Ожидаем, пока все потоки будут готовы начать одновременно
                    if (info.getResponseFormat().equals("CHIP_FIND")) {
                        String str = restTemplate.getForObject(info.getApiAddress(), String.class);
                        List<ChipFindFormat> chipFindFormats = parser.parse(str);
                        List<RadioComponentFormat> res = chipFindFormats
                                .stream()
                                .map(responseMapper::toCommonFormat)
                                .toList();
                        results.add(new SearchDetailDTORes(
                                sellerInfoMapper.toDisplayDTO(info),
                                res));
                    }
                    if (info.getResponseFormat().equals("FREE_CHIPS")) {
                        List<FreeChipsFormat> res = restTemplate.exchange(
                                info.getApiAddress(),
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<FreeChipsFormat>>() {
                                }
                        ).getBody();
                        if (res != null && !res.isEmpty()) {
                            List<RadioComponentFormat> result = res.stream()
                                    .map(responseMapper::toCommonFormat)
                                    .toList();
                            results.add(new SearchDetailDTORes(
                                    sellerInfoMapper.toDisplayDTO(info),
                                    result));
                        }
                    }
                    if (info.getResponseFormat().equals("RADIO_COMPONENT")) {
                        List<RadioComponentFormat> res = restTemplate.exchange(
                                info.getApiAddress(),
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<RadioComponentFormat>>() {
                                }
                        ).getBody();
                        results.add(new SearchDetailDTORes(
                                sellerInfoMapper.toDisplayDTO(info),
                                res));
                    }
                    return null;
                } catch (Exception e) {
                    System.out.println(e);
                    return null; // Если возникла ошибка, возвращаем null
                }
            });
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                future.get(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                // Если возникла ошибка или истекло время ожидания, продолжаем
            }
        }
        executor.shutdown();
        return results;
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }


}

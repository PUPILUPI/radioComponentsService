package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.belov.radioComponentsService.domain.apiFormat.*;
import ru.belov.radioComponentsService.domain.dto.SearchDetailDTORes;
import ru.belov.radioComponentsService.domain.dto.sql.FilterSellerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.mapper.SellerInfoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@Service
@RequiredArgsConstructor
public class ApiService {

    private final RestTemplate restTemplate;
    private final SellerInfoService sellerInfoService;
    private final ChipFindParser parser;
    private final ResponseMapper responseMapper;
    private final SellerInfoMapper sellerInfoMapper;

    public List<SearchDetailDTORes> fetchUrls(Boolean indFlag,
                                              Double rating,
                                              Boolean flagManufacturer,
                                              String city,
                                              Boolean favoriteFlag,
                                              Boolean blacklistFlag) {
        List<SellerInfo> infos = filterUrls(indFlag, rating, flagManufacturer, city, favoriteFlag, blacklistFlag);
        CountDownLatch latch = new CountDownLatch(infos.size());
        List<Future<String>> futures = new ArrayList<>();
        List<SearchDetailDTORes> results = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(infos.size());
        for (SellerInfo info : infos) {
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
        executor.shutdown(); // Завершаем работу ExecutorService
        return results;// Делайте что-то с результатами
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private List<SellerInfo> filterUrls(Boolean indFlag,
                                        Double rating,
                                        Boolean flagManufacturer,
                                        String city,
                                        Boolean favoriteFlag,
                                        Boolean blacklistFlag) {
        List<SellerInfo> data = sellerInfoService.getDataForReq(new FilterSellerInfoDTO(
                        indFlag,
                        rating,
                        flagManufacturer,
                        city))
                .stream()
                .filter(sellerInfo -> isNotEmpty(sellerInfo.getResponseFormat()))
                .filter(sellerInfo -> isNotEmpty(sellerInfo.getApiAddress()))
                .filter(SellerInfo::getAgreementFlag)
                .toList();
        return data;
    }


}

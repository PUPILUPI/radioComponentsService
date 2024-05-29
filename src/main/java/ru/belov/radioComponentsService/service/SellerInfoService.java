package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeSellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.DisplaySellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.FilterSellerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.mapper.SellerInfoMapper;
import ru.belov.radioComponentsService.repository.SellerInfoRepository;
import ru.belov.radioComponentsService.repository.UserRepository;
import ru.belov.radioComponentsService.specification.SellerInfoSpecification;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SellerInfoService {
    private final SellerInfoRepository sellerInfoRepository;
    private final UserRepository userRepository;
    private final SellerInfoMapper sellerInfoMapper;


    public ChangeSellerInfoDTO create(ChangeSellerInfoDTO dto) {
        MyUser user = userRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        SellerInfo sellerInfo = sellerInfoMapper.toEntity(dto);
        if (!Objects.equals(user.getUserRole(), "MANUFACTURER")) sellerInfo.setFlagManufacturer(false);
        sellerInfo.setUser(user);
        return sellerInfoMapper.toDTO(sellerInfoRepository.save(sellerInfo));
    }

    public ChangeSellerInfoDTO getSellerInfo(Long id) {
        return sellerInfoRepository.findById(id)
                .map(sellerInfoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public ChangeSellerInfoDTO updateSellerInfo(ChangeSellerInfoDTO dto) {
        SellerInfo newSellerInfo = sellerInfoRepository.findById(dto.id()).map(oldInfo -> {
                    oldInfo.setBankName(dto.bankName());
                    oldInfo.setRcBic(dto.rcBic());
                    oldInfo.setCorrAcc(dto.corrAcc());
                    oldInfo.setCompanyName(dto.companyName());
                    oldInfo.setZip(dto.zip());
                    oldInfo.setCity(dto.city());
                    oldInfo.setBusinessAddress(dto.businessAddress());
                    oldInfo.setInn(dto.inn());
                    oldInfo.setKpp(dto.kpp());
                    oldInfo.setAcc(dto.acc());
                    oldInfo.setPhoneNumber(dto.phoneNumber());
                    oldInfo.setEmail(dto.email());
                    oldInfo.setIndFlag(dto.indFlag());
                    oldInfo.setApiAddress(dto.apiAddress());
                    return sellerInfoRepository.save(oldInfo);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
        return sellerInfoMapper.toDTO(newSellerInfo);
    }

    public List<DisplaySellerInfoDTO> filter(FilterSellerInfoDTO filter, Pageable pageable) {
        Specification<SellerInfo> spec = Specification.where(null);
        if (filter.indFlag() != null) {
            spec = spec.and(SellerInfoSpecification.hasIndFlag(filter.indFlag()));
        }
        if (filter.flagManufacturer() != null) {
            spec = spec.and(SellerInfoSpecification.hasflagManufacturer(filter.flagManufacturer()));
        }
        if (filter.rating() != null) {
            spec = spec.and(SellerInfoSpecification.hasRatingGreaterThanOrEqual(filter.rating()));
        }
        if (filter.city() != null) {
            spec = spec.and(SellerInfoSpecification.hasCity(filter.city()));
        }
        return sellerInfoMapper.toDTO(sellerInfoRepository.findAll(spec, pageable).toList());
    }
}

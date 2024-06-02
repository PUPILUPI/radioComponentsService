package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeSellerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.CreateSellerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.DisplaySellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.FilterSellerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.exceptions.GeneralException;
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


    public SellerInfo create(CreateSellerInfoDTOReq req) {
        MyUser user = userRepository.findById(req.id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        SellerInfo sellerInfo = sellerInfoMapper.toEntity(req);
        sellerInfo.setFlagManufacturer(Objects.equals(user.getUserRole(), "MANUFACTURER"));
        sellerInfo.setUser(user);
        return sellerInfoRepository.save(sellerInfo);
    }

    public SellerInfo getSellerInfo(Long id) {
        return sellerInfoRepository.findById(id)
                .orElseThrow(() -> new GeneralException(404, "User not found"));
    }

    public SellerInfo updateSellerInfo(Long id, ChangeSellerInfoDTOReq req) {
        return sellerInfoRepository.findById(id).map(oldInfo -> {
                    oldInfo.setBankName(req.bankName());
                    oldInfo.setRcBic(req.rcBic());
                    oldInfo.setCorrAcc(req.corrAcc());
                    oldInfo.setCompanyName(req.companyName());
                    oldInfo.setZip(req.zip());
                    oldInfo.setCity(req.city());
                    oldInfo.setBusinessAddress(req.businessAddress());
                    oldInfo.setInn(req.inn());
                    oldInfo.setKpp(req.kpp());
                    oldInfo.setAcc(req.acc());
                    oldInfo.setPhoneNumber(req.phoneNumber());
                    oldInfo.setEmail(req.email());
                    oldInfo.setIndFlag(req.indFlag());
                    oldInfo.setResponseFormat(req.responseFormat());
                    oldInfo.setApiAddress(req.apiAddress());
                    return sellerInfoRepository.save(oldInfo);
                })
                .orElseThrow(() -> new GeneralException(404, "User not found"));
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
        if(filter.companyName() != null) {
            spec = spec.and(SellerInfoSpecification.hasCompanyNameLike(filter.companyName()));
        }
        return sellerInfoMapper.toDisplayDTOs(sellerInfoRepository.findAll(spec, pageable).toList());
    }
    public List<SellerInfo> getDataForReq(FilterSellerInfoDTO filter) {
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
        return sellerInfoRepository.findAll(spec);
    }
}

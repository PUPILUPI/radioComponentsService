package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.SellerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;

@Component
public class SellerInfoMapper {
    public SellerInfo toEntity(SellerInfoDTO dto) {
        return new SellerInfo().toBuilder()
                .bankName(dto.bankName())
                .rcBic(dto.rcBic())
                .corrAcc(dto.corrAcc())
                .companyName(dto.companyName())
                .zip(dto.zip())
                .city(dto.city())
                .businessAddress(dto.businessAddress())
                .inn(dto.inn())
                .kpp(dto.kpp())
                .acc(dto.acc())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .indFlag(dto.indFlag())
                .apiAddress(dto.apiAddress())
                .build();
    }

    public SellerInfoDTO toDTO(SellerInfo sellerInfo) {
        return new SellerInfoDTO(
                sellerInfo.getId(),
                sellerInfo.getBankName(),
                sellerInfo.getRcBic(),
                sellerInfo.getCorrAcc(),
                sellerInfo.getCompanyName(),
                sellerInfo.getZip(),
                sellerInfo.getCity(),
                sellerInfo.getBusinessAddress(),
                sellerInfo.getInn(),
                sellerInfo.getKpp(),
                sellerInfo.getAcc(),
                sellerInfo.getPhoneNumber(),
                sellerInfo.getEmail(),
                sellerInfo.getIndFlag(),
                sellerInfo.getApiAddress()
        );
    }

}

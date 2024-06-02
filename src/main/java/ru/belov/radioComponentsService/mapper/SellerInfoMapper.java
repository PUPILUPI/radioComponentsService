package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.*;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;

import java.util.List;

@Component
public class SellerInfoMapper {
    public SellerInfo toEntity(CreateSellerInfoDTOReq dto) {
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
                .responseFormat(dto.responseFormat())
                .apiAddress(dto.apiAddress())
                .build();
    }

    public CreateSellerInfoDTORes toCreatedDTO(SellerInfo entity) {
        return new CreateSellerInfoDTORes(
                entity.getId(),
                entity.getBankName(),
                entity.getRcBic(),
                entity.getCorrAcc(),
                entity.getCompanyName(),
                entity.getZip(),
                entity.getCity(),
                entity.getBusinessAddress(),
                entity.getInn(),
                entity.getKpp(),
                entity.getAcc(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getIndFlag(),
                entity.getResponseFormat(),
                entity.getApiAddress()
        );
    }

    public ChangeSellerInfoDTORes toChangedDTO(SellerInfo entity) {
        return new ChangeSellerInfoDTORes(
                entity.getBankName(),
                entity.getRcBic(),
                entity.getCorrAcc(),
                entity.getCompanyName(),
                entity.getZip(),
                entity.getCity(),
                entity.getBusinessAddress(),
                entity.getInn(),
                entity.getKpp(),
                entity.getAcc(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getIndFlag(),
                entity.getResponseFormat(),
                entity.getApiAddress()
        );
    }

    public List<DisplaySellerInfoDTO> toDisplayDTOs(List<SellerInfo> info) {
        return info.stream().map(this::toDisplayDTO).toList();
    }

    public DisplaySellerInfoDTO toDisplayDTO(SellerInfo sellerInfo) {
        return new DisplaySellerInfoDTO(
                sellerInfo.getId(),
                sellerInfo.getCompanyName(),
                sellerInfo.getZip(),
                sellerInfo.getCity(),
                sellerInfo.getBusinessAddress(),
                sellerInfo.getInn(),
                sellerInfo.getKpp(),
                sellerInfo.getPhoneNumber(),
                sellerInfo.getEmail(),
                sellerInfo.getIndFlag(),
                sellerInfo.getFlagManufacturer(),
                sellerInfo.getRating()
                );
    }

}

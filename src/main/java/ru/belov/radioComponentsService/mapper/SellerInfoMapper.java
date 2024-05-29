package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeSellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.DisplaySellerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;

import java.util.List;

@Component
public class SellerInfoMapper {
    public SellerInfo toEntity(ChangeSellerInfoDTO dto) {
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

    public ChangeSellerInfoDTO toDTO(SellerInfo sellerInfo) {
        return new ChangeSellerInfoDTO(
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

    public List<DisplaySellerInfoDTO> toDTO(List<SellerInfo> info) {
        return info.stream().map(this::toDisplayDTO).toList();
    }

    private DisplaySellerInfoDTO toDisplayDTO(SellerInfo sellerInfo) {
        return new DisplaySellerInfoDTO(
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

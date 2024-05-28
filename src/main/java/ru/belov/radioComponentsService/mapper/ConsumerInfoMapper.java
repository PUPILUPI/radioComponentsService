package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.ConsumerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.ConsumerInfo;

@Component
public class ConsumerInfoMapper {
    public ConsumerInfo toEntity(ConsumerInfoDTO dto) {
        return new ConsumerInfo().toBuilder()
                .companyName(dto.companyName())
                .zip(dto.zip())
                .city(dto.city())
                .businessAddress(dto.businessAddress())
                .inn(dto.inn())
                .kpp(dto.kpp())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .build();
    }

    public ConsumerInfoDTO toDTO(ConsumerInfo consumerInfo) {
        return new ConsumerInfoDTO(
                consumerInfo.getId(),
                consumerInfo.getCompanyName(),
                consumerInfo.getZip(),
                consumerInfo.getCity(),
                consumerInfo.getBusinessAddress(),
                consumerInfo.getInn(),
                consumerInfo.getKpp(),
                consumerInfo.getPhoneNumber(),
                consumerInfo.getEmail()
        );
    }
}

package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeConsumerInfoDTORes;
import ru.belov.radioComponentsService.domain.dto.sql.CreateConsumerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.CreateConsumerInfoDTORes;
import ru.belov.radioComponentsService.domain.entity.sql.ConsumerInfo;

@Component
public class ConsumerInfoMapper {
    public ConsumerInfo toEntity(CreateConsumerInfoDTOReq dto) {
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

    public CreateConsumerInfoDTORes toCreatedDTO (ConsumerInfo info) {
        return new CreateConsumerInfoDTORes(
                info.getId(),
                info.getCompanyName(),
                info.getZip(),
                info.getCity(),
                info.getBusinessAddress(),
                info.getInn(),
                info.getKpp(),
                info.getPhoneNumber(),
                info.getEmail()
        );
    }
    public ChangeConsumerInfoDTORes toChangedDTO(ConsumerInfo info) {
        return new ChangeConsumerInfoDTORes(
                info.getCompanyName(),
                info.getZip(),
                info.getCity(),
                info.getBusinessAddress(),
                info.getInn(),
                info.getKpp(),
                info.getPhoneNumber(),
                info.getEmail()
        );
    }
}

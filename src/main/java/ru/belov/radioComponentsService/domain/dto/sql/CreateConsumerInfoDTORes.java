package ru.belov.radioComponentsService.domain.dto.sql;

public record CreateConsumerInfoDTORes(
        Long id,
        String companyName,
        String zip,
        String city,
        String businessAddress,
        String inn,
        String kpp,
        String phoneNumber,
        String email
) {}

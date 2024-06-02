package ru.belov.radioComponentsService.domain.dto.sql;

public record DisplaySellerInfoDTO(
        Long id,
        String companyName,
        String zip,
        String city,
        String businessAddress,
        String inn,
        String kpp,
        String phoneNumber,
        String email,
        Boolean indFlag,
        Boolean flagManufacturer,
        Double rating
) {
}

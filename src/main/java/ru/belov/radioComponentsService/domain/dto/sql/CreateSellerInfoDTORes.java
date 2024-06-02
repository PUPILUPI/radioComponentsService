package ru.belov.radioComponentsService.domain.dto.sql;

public record CreateSellerInfoDTORes(
        Long id,
        String bankName,
        String rcBic,
        String corrAcc,
        String companyName,
        String zip,
        String city,
        String businessAddress,
        String inn,
        String kpp,
        String acc,
        String phoneNumber,
        String email,
        Boolean indFlag,
        String responseFormat,
        String apiAddress
) {
}

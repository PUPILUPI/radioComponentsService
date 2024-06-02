package ru.belov.radioComponentsService.domain.dto.sql;

public record CreateSellerInfoDTOReq(
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
    public CreateSellerInfoDTOReq(Long userId) {
        this(userId,null, null, null, null, null,null, null, null, null, null,null,null,null,null,null);
    }
}

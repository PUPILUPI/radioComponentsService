package ru.belov.radioComponentsService.domain.dto.sql;

public record AddSellerToListReq(
        Long sellerId,
        Boolean favoriteFlag,

        Boolean blackListFlag

) {
}

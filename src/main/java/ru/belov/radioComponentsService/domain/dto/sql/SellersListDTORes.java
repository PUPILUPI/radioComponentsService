package ru.belov.radioComponentsService.domain.dto.sql;

public record SellersListDTORes(
        Long sellerId,
        String name,
        Boolean favoriteFlag,
        Boolean blacklistFlag
) {
}

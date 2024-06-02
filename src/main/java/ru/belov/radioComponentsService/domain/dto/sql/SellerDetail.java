package ru.belov.radioComponentsService.domain.dto.sql;

public record SellerDetail(Long id,
                           String name,
                           Integer count,
                           Double price,
                           Integer twoCount,
                           Double twoPrice,
                           Integer threeCount,
                           Double threePrice
) {
}

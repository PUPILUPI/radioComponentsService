package ru.belov.radioComponentsService.domain.dto.sql;

public record FilterSellerInfoDTO(    Boolean indFlag,
                                      Double rating,
                                      Boolean flagManufacturer,
                                      String city)
{}

package ru.belov.radioComponentsService.domain.apiFormat;

import lombok.Data;

import java.util.List;
//класс соответсвующий формату freeChips

@Data
public class FreeChipsFormat {
    private String id;
    private String name;
    private String group;
    private String manPn;
    private String manufacturer;
    private String description;
    private String caseType;
    private String packType;
    private Integer packQuant;
    private Integer stockQuant;
    private Integer orderQuant;
    private Integer orderDays;
    private Integer minQuant;
    private Integer multQuant;
    private String itemUrl;
    private String itemPdf;
    private String itemImage;
    private List<Price> prices;

    public static class Price {
        public Integer breakQty;
        public Double value;
        public String currency;
    }
}

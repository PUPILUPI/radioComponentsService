package ru.belov.radioComponentsService.domain.apiFormat;

import lombok.Data;

@Data
public class RadioComponentFormat {
    private String name;
    private boolean inStockFlag;
    private String orderDays;
    private Integer quantity;
    private Integer minQuantity;
    private Double firstPrice;
    private Integer secondQuantity;
    private Double secondPrice;
    private Integer thirdQuantity;
    private Double thirdPrice;

}

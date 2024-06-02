package ru.belov.radioComponentsService.domain.dto;

import ru.belov.radioComponentsService.domain.apiFormat.RadioComponentFormat;
import ru.belov.radioComponentsService.domain.dto.sql.DisplaySellerInfoDTO;

import java.util.List;

public record SearchDetailDTORes(
        DisplaySellerInfoDTO sellerInfo,
        List<RadioComponentFormat> response
) {
}

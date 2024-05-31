package ru.belov.radioComponentsService.domain.dto.noSql;

import java.util.List;

public record DetailDTORes(
        String name,
        List<String> categories,
        String manufacturer
) {
}

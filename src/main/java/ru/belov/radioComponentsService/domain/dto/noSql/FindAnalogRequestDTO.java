package ru.belov.radioComponentsService.domain.dto.noSql;

import java.util.List;

public record FindAnalogRequestDTO(
        String name,
        List<String> analogTypes
) {
}

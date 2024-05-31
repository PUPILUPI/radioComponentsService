package ru.belov.radioComponentsService.domain.dto.noSql;

import java.util.List;

public record FindAnalogDTOReq(
        String name,
        List<String> analogTypes
) {
}

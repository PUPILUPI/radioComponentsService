package ru.belov.radioComponentsService.domain.dto.sql;

import jakarta.validation.constraints.Size;

public record PasswordDTOReq(
        @Size(min = 6, max = 20)
        String password) {
}

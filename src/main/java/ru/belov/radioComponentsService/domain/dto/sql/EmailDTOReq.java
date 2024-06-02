package ru.belov.radioComponentsService.domain.dto.sql;

import jakarta.validation.constraints.Email;

public record EmailDTOReq(
        @Email
        String email
) {
}

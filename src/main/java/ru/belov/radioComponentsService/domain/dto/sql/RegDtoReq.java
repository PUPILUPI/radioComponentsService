package ru.belov.radioComponentsService.domain.dto.sql;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record RegDtoReq(
        Long id,
        String roleUser,
        String surname,

        String firstName,
        String middleName,
        @Email
        String email,
        @Size(min = 6, max = 20)
        String password,
        String phoneNumber) {
}

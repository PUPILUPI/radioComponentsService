package ru.belov.radioComponentsService.domain.dto.sql;

public record UserDTO(
        Long id,
        String roleUser,
        String surname,
        String firstName,
        String middleName,
        String email,
        String password,
        String phoneNumber) {
}

package ru.belov.radioComponentsService.dto;

public record UserDTO( String roleUser, String surname,
                      String firstName, String middleName,
                      String email, String password, String phoneNumber) {
}

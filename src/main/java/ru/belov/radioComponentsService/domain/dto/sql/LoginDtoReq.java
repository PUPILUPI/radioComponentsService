package ru.belov.radioComponentsService.domain.dto.sql;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDtoReq {
    @Email
    private String email;
    private String password;
}

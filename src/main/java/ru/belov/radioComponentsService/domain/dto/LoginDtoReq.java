package ru.belov.radioComponentsService.domain.dto;

import lombok.*;

/**
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDtoReq {
    private String email;
    private String password;
}

package ru.belov.radioComponentsService.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtTokenDtoRes {
    private String access;
    private String refresh;
}

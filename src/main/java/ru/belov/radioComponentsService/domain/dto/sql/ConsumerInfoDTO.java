package ru.belov.radioComponentsService.domain.dto.sql;

public record ConsumerInfoDTO(Long id,
                              String companyName,
                              String zip,
                              String city,
                              String businessAddress,
                              String inn,
                              String kpp,
                              String phoneNumber,
                              String email
) {
    public ConsumerInfoDTO(Long userId) {
        this(userId, null, null, null, null, null, null, null, null);
    }
}

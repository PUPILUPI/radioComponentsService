package ru.belov.radioComponentsService.domain.entity.sql;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellersListId implements Serializable {
    private Long consumerId;
    private Long sellerId;
}

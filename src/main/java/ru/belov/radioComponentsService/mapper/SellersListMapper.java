package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.SellersListDTORes;
import ru.belov.radioComponentsService.domain.entity.sql.SellersList;

@Component
public class SellersListMapper {
    public SellersListDTORes toDTO(SellersList entity) {
        return new SellersListDTORes(
                entity.getId().getSellerId(),
                entity.getSeller().getCompanyName(),
                entity.getFavoriteFlag(),
                entity.getBlacklistFlag()
        );
    }
}

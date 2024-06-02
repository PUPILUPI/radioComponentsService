package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.noSql.DetailDTORes;
import ru.belov.radioComponentsService.domain.entity.noSql.Detail;

@Component
public class DetailMapper {
    public Detail toEntity(DetailDTORes dto) {
        return new Detail().toBuilder()
                .name(dto.name())
                .categories(dto.categories())
                .manufacturer(dto.manufacturer())
                .build();
    }

    public DetailDTORes toDTO(Detail detail) {
        return new DetailDTORes(
                detail.getName(),
                detail.getCategories(),
                detail.getManufacturer()
        );
    }
}

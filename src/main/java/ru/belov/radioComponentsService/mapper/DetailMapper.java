package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.noSql.DetailDTO;
import ru.belov.radioComponentsService.domain.entity.noSql.Detail;

@Component
public class DetailMapper {
    public Detail toEntity(DetailDTO dto) {
        return new Detail().toBuilder()
                .name(dto.name())
                .categories(dto.categories())
                .manufacturer(dto.manufacturer())
                .build();
    }

    public DetailDTO toDTO(Detail detail) {
        return new DetailDTO(
                detail.getName(),
                detail.getCategories(),
                detail.getManufacturer()
        );
    }
}

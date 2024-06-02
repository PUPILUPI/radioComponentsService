package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.domain.dto.noSql.AnalogDTORes;
import ru.belov.radioComponentsService.domain.dto.noSql.FindAnalogDTOReq;
import ru.belov.radioComponentsService.domain.entity.noSql.Detail;
import ru.belov.radioComponentsService.repository.DetailRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepository detailRepository;

    public List<Detail> getDetailsByCategory(String category, Pageable pageable) {
        return detailRepository.findByCategory(category, pageable);
    }

    public List<AnalogDTORes> getAnalogs(FindAnalogDTOReq dto) {
        List<AnalogDTORes> result = new ArrayList<>();
        if (dto.analogTypes().isEmpty()) {
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(), "PIN_TO_PIN"));
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(), "NEARLY_ANALOG"));
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(), "FUNCTIONAL_ANALOG"));
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(), "POSSIBLE_ANALOG"));
        } else {
            dto.analogTypes().forEach(type ->
                    result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(), type)));
        }
        return result;
    }
}

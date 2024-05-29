package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.domain.dto.noSql.AnalogResponseDTO;
import ru.belov.radioComponentsService.domain.dto.noSql.DetailDTO;
import ru.belov.radioComponentsService.domain.dto.noSql.FindAnalogRequestDTO;
import ru.belov.radioComponentsService.mapper.DetailMapper;
import ru.belov.radioComponentsService.repository.DetailRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepository detailRepository;
    private final DetailMapper detailMapper;

    public List<DetailDTO> getDetailsByCategory(String category, Pageable pageable) {
        return detailRepository.findByCategory(category, pageable)
                .stream()
                .map(detailMapper::toDTO)
                .toList();
    }

    public List<AnalogResponseDTO> getAnalogs(FindAnalogRequestDTO dto) {
        List<AnalogResponseDTO> result = new ArrayList<>();
        if (dto.analogTypes().isEmpty()) {
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(),"PIN_TO_PIN"));
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(),"NEARLY_ANALOG"));
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(),"FUNCTIONAL_ANALOG"));
            result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(),"POSSIBLE_ANALOG"));
        } else {
            dto.analogTypes().forEach(type ->
                    result.addAll(detailRepository.findAnalogsByNameAndType(dto.name(), type)));
        }
        return result;
    }
}

package ru.belov.radioComponentsService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.noSql.AnalogResponseDTO;
import ru.belov.radioComponentsService.domain.dto.noSql.DetailDTO;
import ru.belov.radioComponentsService.domain.dto.noSql.FindAnalogRequestDTO;
import ru.belov.radioComponentsService.service.DetailService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/detail")
@RequiredArgsConstructor
public class DetailController {
    private final DetailService service;

    @GetMapping("/{category}")
    public List<DetailDTO> getDetailsByCategory(@PathVariable String category,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "20") int size) {
        return service.getDetailsByCategory(category, page, size);
    }

    @PostMapping("/analog")
    public List<AnalogResponseDTO> getAnalogs(@RequestBody FindAnalogRequestDTO dto) {
        return service.getAnalogs(dto);
    }


}

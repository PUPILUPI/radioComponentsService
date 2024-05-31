package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.noSql.AnalogDTORes;
import ru.belov.radioComponentsService.domain.dto.noSql.DetailDTORes;
import ru.belov.radioComponentsService.domain.dto.noSql.FindAnalogDTOReq;
import ru.belov.radioComponentsService.mapper.DetailMapper;
import ru.belov.radioComponentsService.service.DetailService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/detail")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DetailController {
    private final DetailService service;
    private final DetailMapper detailMapper;

    @GetMapping("/{category}")
    public List<DetailDTORes> getDetailsByCategory(@PathVariable String category,
                                                   @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return service.getDetailsByCategory(category, pageable)
                .stream()
                .map(detailMapper::toDTO)
                .toList();
    }

    @PostMapping("/analog")
    public List<AnalogDTORes> getAnalogs(@RequestBody FindAnalogDTOReq dto) {

        return service.getAnalogs(dto);
    }


}

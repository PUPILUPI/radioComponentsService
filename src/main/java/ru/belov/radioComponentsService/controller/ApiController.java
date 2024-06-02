package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belov.radioComponentsService.domain.apiFormat.ChipFindParser;
import ru.belov.radioComponentsService.domain.apiFormat.RadioComponentFormat;
import ru.belov.radioComponentsService.domain.dto.SearchDetailDTORes;
import ru.belov.radioComponentsService.domain.dto.sql.SellerDetail;
import ru.belov.radioComponentsService.exceptions.GeneralException;
import ru.belov.radioComponentsService.service.ApiService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ApiController {
    private final ApiService apiService;
    private final ChipFindParser parser;

    @GetMapping
    public List<SearchDetailDTORes> getPostsPlainJSON() {
        return apiService.fetchUrls(null, null, null, null, null, null);
    }

    @GetMapping("/test")
    public String test() {
        throw new GeneralException(409, "Привет");
    }
}

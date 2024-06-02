package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.apiFormat.ChipFindParser;
import ru.belov.radioComponentsService.domain.dto.SearchDetailDTORes;
import ru.belov.radioComponentsService.security.CustomUserDetails;
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
    public List<SearchDetailDTORes> find(@RequestParam String detailName,
                                         @RequestParam(required = false) Boolean indFlag,
                                         @RequestParam(required = false) Double rating,
                                         @RequestParam(required = false) Boolean flagManufacturer,
                                         @RequestParam(required = false) String city,
                                         @RequestParam(required = false) Boolean favoriteFlag,
                                         @RequestParam(required = false) Boolean blacklistFlag,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return apiService.doRequests(
                customUserDetails.getUser().getUserId(),
                indFlag,
                rating,
                flagManufacturer,
                city,
                favoriteFlag,
                blacklistFlag);
    }

}

package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belov.radioComponentsService.domain.dto.sql.SellerDetail;
import ru.belov.radioComponentsService.service.ApiService;

@RestController
@CrossOrigin
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ApiController {
    private final ApiService apiService;

    @GetMapping
    public SellerDetail[] getPostsPlainJSON() {
        return apiService.getPostsPlainJSON();
    }
}

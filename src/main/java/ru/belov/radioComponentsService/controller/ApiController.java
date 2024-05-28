package ru.belov.radioComponentsService.controller;

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
public class ApiController {
    private final ApiService apiService;

    @GetMapping
    public SellerDetail[] getPostsPlainJSON() {
        return apiService.getPostsPlainJSON();
    }
}

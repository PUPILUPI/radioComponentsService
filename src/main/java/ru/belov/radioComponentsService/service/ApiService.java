package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.belov.radioComponentsService.domain.dto.sql.SellerDetail;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final RestTemplate restTemplate;

    public SellerDetail[] getPostsPlainJSON() {
        String url = "http://localhost:8081/api/v1/detail";
        return this.restTemplate.getForObject(url, SellerDetail[].class);
    }
}

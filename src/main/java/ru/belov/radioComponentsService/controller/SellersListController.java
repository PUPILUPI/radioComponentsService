package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.AddSellerToListReq;
import ru.belov.radioComponentsService.domain.dto.sql.SellersListDTORes;
import ru.belov.radioComponentsService.domain.entity.sql.SellersList;
import ru.belov.radioComponentsService.mapper.SellersListMapper;
import ru.belov.radioComponentsService.security.CustomUserDetails;
import ru.belov.radioComponentsService.service.SellersListService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sellersList")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SellersListController {
    private final SellersListService service;
    private final SellersListMapper mapper;

    @PostMapping()
    public ResponseEntity<String> addSellerToList(@RequestBody AddSellerToListReq req,
                                                  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        service.add(customUserDetails.getUser().getUserId(), req);
        return ResponseEntity.status(HttpStatus.CREATED).body("Продавец успешно добавлен в список");
    }

    @GetMapping()
    public ResponseEntity<List<SellersListDTORes>> getSellersByUserIdAndTypeList(@RequestParam Boolean favoriteFlag,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<SellersListDTORes> res = service.getByConsumerIdAndTypeList(customUserDetails.getUser().getUserId(), favoriteFlag)
                .stream()
                .map(mapper::toDTO)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}

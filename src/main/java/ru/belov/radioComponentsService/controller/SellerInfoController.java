package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.*;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.mapper.SellerInfoMapper;
import ru.belov.radioComponentsService.security.CustomUserDetails;
import ru.belov.radioComponentsService.service.SellerInfoService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/seller")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SellerInfoController {
    private final SellerInfoService service;
    private final SellerInfoMapper sellerInfoMapper;

    @GetMapping()
    public ResponseEntity<ChangeSellerInfoDTORes> getSellerInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyUser user = customUserDetails.getUser();
        ChangeSellerInfoDTORes res = sellerInfoMapper.toChangedDTO(
                service.getSellerInfo(user.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping
    public ResponseEntity<CreateSellerInfoDTORes> createSellerInfo(@RequestBody CreateSellerInfoDTOReq req) {
        CreateSellerInfoDTORes res = sellerInfoMapper.toCreatedDTO(service.create(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


    @PutMapping
    public ResponseEntity<ChangeSellerInfoDTORes> updateSellerInfo(@RequestBody ChangeSellerInfoDTOReq req,
                                                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyUser user = customUserDetails.getUser();
        ChangeSellerInfoDTORes res = sellerInfoMapper.toChangedDTO(
                service.updateSellerInfo(user.getUserId(), req));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Transactional
    @PostMapping("/filter")
    public ResponseEntity<List<DisplaySellerInfoDTO>> getFilteredSellerInfos(@PageableDefault(page = 0, size = 10, sort = "rating", direction = Sort.Direction.DESC) Pageable pageable,
                                                                             @RequestBody FilterSellerInfoDTO filter) {
        return ResponseEntity.status(HttpStatus.OK).body(service.filter(filter, pageable));
    }
}

package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeConsumerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeConsumerInfoDTORes;
import ru.belov.radioComponentsService.domain.dto.sql.CreateConsumerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.CreateConsumerInfoDTORes;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.mapper.ConsumerInfoMapper;
import ru.belov.radioComponentsService.security.CustomUserDetails;
import ru.belov.radioComponentsService.service.ConsumerInfoService;

@RestController
@CrossOrigin
@RequestMapping("/consumer")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ConsumerInfoController {
    private final ConsumerInfoService service;
    private final ConsumerInfoMapper consumerInfoMapper;


    @PostMapping
    public ResponseEntity<CreateConsumerInfoDTORes> createConsumerInfo(@RequestBody CreateConsumerInfoDTOReq req) {
        CreateConsumerInfoDTORes res = consumerInfoMapper.toCreatedDTO(service.create(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    //    @Secured("ROLE_AUTH")
    @GetMapping()
    public ResponseEntity<ChangeConsumerInfoDTORes> getConsumerInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyUser user = customUserDetails.getUser();
        ChangeConsumerInfoDTORes res = consumerInfoMapper.toChangedDTO(
                service.getConsumerInfo(user.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping
    public ResponseEntity<ChangeConsumerInfoDTORes> updateConsumerInfo(@RequestBody ChangeConsumerInfoDTOReq req,
                                                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyUser user = customUserDetails.getUser();
        ChangeConsumerInfoDTORes res = consumerInfoMapper.toChangedDTO(
                service.updateConsumerInfo(user.getUserId(), req));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


}

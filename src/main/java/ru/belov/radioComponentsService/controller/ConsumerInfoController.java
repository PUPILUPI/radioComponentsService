package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.ConsumerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.security.CustomUserDetails;
import ru.belov.radioComponentsService.service.ConsumerInfoService;

@RestController
@CrossOrigin
@RequestMapping("/consumer")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ConsumerInfoController {
    private final ConsumerInfoService service;


    @PostMapping
    @Secured("ROLE_AUTH")
    public ResponseEntity<ConsumerInfoDTO> createConsumerInfo(@RequestBody ConsumerInfoDTO dto,
                                                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyUser user = customUserDetails.getUser(); // этот чел делает запрос
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumerInfoDTO> getConsumerInfo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getConsumerInfo(id));
    }

    @PutMapping
    public ResponseEntity<ConsumerInfoDTO> updateConsumerInfo(@RequestBody ConsumerInfoDTO consumerInfoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateConsumerInfo(consumerInfoDTO));
    }


}

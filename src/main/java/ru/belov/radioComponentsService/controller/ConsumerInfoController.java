package ru.belov.radioComponentsService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.ConsumerInfoDTO;
import ru.belov.radioComponentsService.service.ConsumerInfoService;

@RestController
@CrossOrigin
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerInfoController {
    private final ConsumerInfoService service;


    @PostMapping
    public ResponseEntity<ConsumerInfoDTO> createConsumerInfo(@RequestBody ConsumerInfoDTO dto) {
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

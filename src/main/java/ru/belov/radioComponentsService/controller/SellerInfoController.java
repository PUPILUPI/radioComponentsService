package ru.belov.radioComponentsService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.FilterSellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.SellerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.service.SellerInfoService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerInfoController {
    private final SellerInfoService service;
    @GetMapping("/{id}")
    public ResponseEntity<SellerInfoDTO> getSellerInfo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getSellerInfo(id));
    }

    @PostMapping
    public ResponseEntity<SellerInfoDTO> createSellerInfo(@RequestBody SellerInfoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<SellerInfoDTO> updateSellerInfo(@RequestBody SellerInfoDTO sellerInfoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateSellerInfo(sellerInfoDTO));
    }

    @Transactional
    @PostMapping("/filter")
    public ResponseEntity<List<SellerInfo>> getFilteredSellerInfos(@RequestBody FilterSellerInfoDTO filter) {
        return ResponseEntity.status(HttpStatus.OK).body(service.filters(filter));
    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSellerInfo(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.);
//    }

//    @PatchMapping("/update")
//    public Seller update()
}

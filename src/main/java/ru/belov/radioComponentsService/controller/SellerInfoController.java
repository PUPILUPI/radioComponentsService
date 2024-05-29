package ru.belov.radioComponentsService.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeSellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.DisplaySellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.FilterSellerInfoDTO;
import ru.belov.radioComponentsService.service.SellerInfoService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/seller")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SellerInfoController {
    private final SellerInfoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ChangeSellerInfoDTO> getSellerInfo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getSellerInfo(id));
    }

    @PostMapping
    public ResponseEntity<ChangeSellerInfoDTO> createSellerInfo(@RequestBody ChangeSellerInfoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<ChangeSellerInfoDTO> updateSellerInfo(@RequestBody ChangeSellerInfoDTO sellerInfoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateSellerInfo(sellerInfoDTO));
    }

    @Transactional
    @PostMapping("/filter")
    public ResponseEntity<List<DisplaySellerInfoDTO>> getFilteredSellerInfos(@PageableDefault(page = 0, size = 10, sort = "rating", direction = Sort.Direction.DESC) Pageable pageable,
                                                                             @RequestBody FilterSellerInfoDTO filter) {
        return ResponseEntity.status(HttpStatus.OK).body(service.filter(filter, pageable));
    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSellerInfo(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.);
//    }

//    @PatchMapping("/update")
//    public Seller update()
}

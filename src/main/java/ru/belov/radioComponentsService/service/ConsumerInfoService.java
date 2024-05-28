package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.domain.dto.sql.ConsumerInfoDTO;
import ru.belov.radioComponentsService.domain.entity.sql.ConsumerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.User;
import ru.belov.radioComponentsService.mapper.ConsumerInfoMapper;
import ru.belov.radioComponentsService.repository.ConsumerInfoRepository;
import ru.belov.radioComponentsService.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ConsumerInfoService {
    private final ConsumerInfoRepository consumerInfoRepository;
    private final UserRepository userRepository;
    private final ConsumerInfoMapper consumerInfoMapper;

    public ConsumerInfoDTO create(ConsumerInfoDTO dto) {
        User user = userRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ConsumerInfo consumerInfo = consumerInfoMapper.toEntity(dto);
        consumerInfo.setUser(user);
        return consumerInfoMapper.toDTO(consumerInfoRepository.save(consumerInfo));
    }

    public ConsumerInfoDTO updateConsumerInfo(ConsumerInfoDTO dto) {
        ConsumerInfo newConsumerInfo = consumerInfoRepository.findById(dto.id()).map(oldInfo -> {
                    oldInfo.setCompanyName(dto.companyName());
                    oldInfo.setZip(dto.zip());
                    oldInfo.setCity(dto.city());
                    oldInfo.setBusinessAddress(dto.businessAddress());
                    oldInfo.setInn(dto.inn());
                    oldInfo.setKpp(dto.kpp());
                    oldInfo.setPhoneNumber(dto.phoneNumber());
                    oldInfo.setEmail(dto.email());
                    return consumerInfoRepository.save(oldInfo);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
        return consumerInfoMapper.toDTO(newConsumerInfo);
    }

    public ConsumerInfoDTO getConsumerInfo(Long id) {
        return consumerInfoRepository.findById(id)
                .map(consumerInfoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}

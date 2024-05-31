package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeConsumerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeConsumerInfoDTORes;
import ru.belov.radioComponentsService.domain.dto.sql.CreateConsumerInfoDTOReq;
import ru.belov.radioComponentsService.domain.entity.sql.ConsumerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.exceptions.GeneralException;
import ru.belov.radioComponentsService.mapper.ConsumerInfoMapper;
import ru.belov.radioComponentsService.repository.ConsumerInfoRepository;
import ru.belov.radioComponentsService.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ConsumerInfoService {
    private final ConsumerInfoRepository consumerInfoRepository;
    private final UserRepository userRepository;
    private final ConsumerInfoMapper consumerInfoMapper;

    public ConsumerInfo create(CreateConsumerInfoDTOReq req) {
        MyUser user = userRepository.findById(req.id())
                .orElseThrow(() -> new GeneralException(404, "User not found"));
        ConsumerInfo consumerInfo = consumerInfoMapper.toEntity(req);
        consumerInfo.setUser(user);
        return consumerInfoRepository.save(consumerInfo);
    }

    public ConsumerInfo updateConsumerInfo(Long id, ChangeConsumerInfoDTOReq req) {
        return consumerInfoRepository.findById(id).map(oldInfo -> {
                    oldInfo.setCompanyName(req.companyName());
                    oldInfo.setZip(req.zip());
                    oldInfo.setCity(req.city());
                    oldInfo.setBusinessAddress(req.businessAddress());
                    oldInfo.setInn(req.inn());
                    oldInfo.setKpp(req.kpp());
                    oldInfo.setPhoneNumber(req.phoneNumber());
                    oldInfo.setEmail(req.email());
                    return consumerInfoRepository.save(oldInfo);
                })
                .orElseThrow(() -> new GeneralException(404, "User not found"));
    }

    public ConsumerInfo getConsumerInfo(Long id) {
        return consumerInfoRepository.findById(id)
                .orElseThrow(() -> new GeneralException(404, "User not found"));
    }

}

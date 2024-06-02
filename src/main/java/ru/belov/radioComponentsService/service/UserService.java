package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.radioComponentsService.domain.dto.sql.CreateConsumerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.CreateSellerInfoDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.RegDtoReq;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.exceptions.GeneralException;
import ru.belov.radioComponentsService.mapper.UserMapper;
import ru.belov.radioComponentsService.repository.UserRepository;


@Service
@EnableScheduling
@RequiredArgsConstructor
public class UserService {
    private final SellerInfoService sellerInfoService;
    private final EmailService emailService;
    private final ConsumerInfoService consumerInfoService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void updateSubmitFlagUser(MyUser user) {
        user.setSubmitFlag(true);
        userRepository.save(user);
    }

    public void updatePassword(Long id, String password) {
        MyUser user = this.getById(id);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Transactional
    public MyUser create(RegDtoReq req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new GeneralException(409, "Пользователь с таким email уже существует");
        }
        MyUser user = userMapper.toEntity(req);
        user.setPassword(passwordEncoder.encode(req.password()));
        user = userRepository.save(user);
        if (user.getUserRole().equals("INDIVIDUAL_CUSTOMER")
                || user.getUserRole().equals("LEGAL_CUSTOMER")) {
            consumerInfoService.create(new CreateConsumerInfoDTOReq(user.getUserId()));
        } else if (user.getUserRole().equals("SUPPLIER")
                || user.getUserRole().equals("MANUFACTURER")) {
            sellerInfoService.create(new CreateSellerInfoDTOReq(user.getUserId()));
        }
        try {
            emailService.confirmEmail(req.email());
        } catch (MailException mailException) {
            throw new GeneralException(550, "Unable to send email");
        }
        return user;
    }
    public MyUser getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(404, "пользователь не найден"));
    }
    public MyUser findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new GeneralException(404, "пользователь не найден"));
    }

}

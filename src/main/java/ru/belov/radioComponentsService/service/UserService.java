package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.radioComponentsService.domain.dto.sql.ConsumerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.ChangeSellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.UserDTO;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.exceptions.GeneralException;
import ru.belov.radioComponentsService.mapper.UserMapper;
import ru.belov.radioComponentsService.repository.UserRepository;

import java.util.Optional;


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

    public void updatePassword(MyUser user, String password) {
//        String newPassword = passwordEncoder.encode(password);
//        entityUser.setPassword(newPassword);
//        userRepository.save(entityUser);
    }

    @Transactional
    public MyUser create(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new GeneralException(409, "Пользователь с таким email уже существует");
        }
        MyUser user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user = userRepository.save(user);
        if (user.getUserRole().equals("INDIVIDUAL_CUSTOMER")
                || user.getUserRole().equals("LEGAL_CUSTOMER")) {
            consumerInfoService.create(new ConsumerInfoDTO(user.getUserId()));
        } else if (user.getUserRole().equals("SUPPLIER")
                || user.getUserRole().equals("MANUFACTURER")) {
            sellerInfoService.create(new ChangeSellerInfoDTO(user.getUserId()));
        }
        try {
            emailService.sendEmail(userDTO.email());
        } catch (MailException mailException) {
            throw new RuntimeException("Unable to send email");
        }
        return user;
    }

    public UserDTO getUserInfo(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public MyUser getById(Long id){
        return userRepository.findById(id)
                .orElseThrow();
    }

    public Optional<MyUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}

package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.radioComponentsService.domain.dto.sql.ConsumerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.SellerInfoDTO;
import ru.belov.radioComponentsService.domain.dto.sql.UserDTO;
import ru.belov.radioComponentsService.domain.entity.sql.User;
import ru.belov.radioComponentsService.mapper.SellerInfoMapper;
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
    private final SellerInfoMapper sellerInfoMapper;
//    private final @Qualifier("transactionManager") PlatformTransactionManager transactionManager;

//    private final PasswordEncoder passwordEncoder;

    public void updateSubmitFlagUser(User user) {
        user.setSubmitFlag(true);
        userRepository.save(user);
    }

    public void updatePassword(User user, String password) {
//        String newPassword = passwordEncoder.encode(password);
//        entityUser.setPassword(newPassword);
//        userRepository.save(entityUser);
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        User user = userMapper.toEntity(userDTO);
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        if (user.getUserRole().equals("INDIVIDUAL_CUSTOMER")
                || user.getUserRole().equals("LEGAL_CUSTOMER")) {
            consumerInfoService.create(new ConsumerInfoDTO(user.getUserId()));
        } else if (user.getUserRole().equals("SUPPLIER")
                || user.getUserRole().equals("MANUFACTURER")) {
            sellerInfoService.create(new SellerInfoDTO(user.getUserId()));
        }
        try {
            emailService.sendEmail(userDTO.email());
        } catch (MailException mailException) {
            throw new RuntimeException("Unable to send email");
        }
        return userMapper.toDTO(user);
    }

    public UserDTO getUserInfo(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

//    public EntityUser findByEmail(String email) {
////        Optional<EntityUser> user=userRepository.findByEmail(email);
////        if(user.isEmpty()){
////
////        }
////        return user.get();
//    }

}

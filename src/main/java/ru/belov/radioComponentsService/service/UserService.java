package ru.belov.radioComponentsService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.db.entity.EntityUser;
import ru.belov.radioComponentsService.db.repository.UserRepository;

import java.util.Optional;


@Service
@EnableScheduling
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updateSubmitFlagUser(EntityUser entityUser) {
        entityUser.setSubmitFlag(true);
        userRepository.save(entityUser);
    }
    public void updatePassword(EntityUser entityUser, String password) {
        String newPassword = passwordEncoder.encode(password);
        entityUser.setPassword(newPassword);
        userRepository.save(entityUser);
    }

    public void addUser(EntityUser entityUser) {
        String email=entityUser.getEmail();
        Optional<EntityUser> user=userRepository.findByEmail(email);
        if(user.isEmpty()){
            userRepository.save(entityUser);
        }
    }

    public EntityUser findByEmail(String email) {
        Optional<EntityUser> user=userRepository.findByEmail(email);
        if(user.isEmpty()){

        }
        return user.get();
    }

}

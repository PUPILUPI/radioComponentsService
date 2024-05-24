package ru.belov.radioComponentsService.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.db.entity.EntityUser;
import ru.belov.radioComponentsService.db.repository.UserRepository;
import ru.belov.radioComponentsService.dto.EmailDTO;
import ru.belov.radioComponentsService.dto.PasswordDTO;
import ru.belov.radioComponentsService.dto.UserDTO;
import ru.belov.radioComponentsService.jwt.Token;
import ru.belov.radioComponentsService.mapper.ConvertEntityUser;
import ru.belov.radioComponentsService.service.EmailService;
import ru.belov.radioComponentsService.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final ConvertEntityUser convertEntityUser;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(UserRepository userRepository, ConvertEntityUser convertEntityUser,
                          UserService userService, EmailService emailService) {
        this.convertEntityUser = convertEntityUser;
        this.userService = userService;
        this.emailService = emailService;

    }

    @ResponseBody
    @GetMapping("/test")
    public String test(){
        return "test";

    }



    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    @PostMapping("/registration")
    public @ResponseBody ResponseEntity registration(@RequestBody UserDTO user) {

        EntityUser entityUser = convertEntityUser.toEntityUser(user);
        userService.addUser(entityUser);
        try {
            emailService.sendEmail();
        } catch (MailException mailException) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

    @GetMapping("/token/{token}")
    public @ResponseBody ResponseEntity token( @PathVariable String token) {
        try {
            Jws<Claims> jwsToken = Token.parseJwt(token);
            String tokenStr=jwsToken.toString();
            int indexStart = tokenStr.indexOf("email") + "email".length()+1;
            int indexEnd = tokenStr.indexOf(",", indexStart);
            String email = tokenStr.substring(indexStart, indexEnd);
            EntityUser entityUser = userService.findByEmail(email);
            userService.updateSubmitFlagUser(entityUser);
            return new ResponseEntity<>(tokenStr, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(token, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recovery")
    public @ResponseBody ResponseEntity recovery(@RequestBody EmailDTO emailDTO) {
        String email = emailDTO.email();
        try {
            emailService.sendEmailRecover();
        } catch (MailException mailException) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

    @PostMapping("/recovery/{token}")
    public @ResponseBody ResponseEntity recovery(@PathVariable String token, @RequestBody PasswordDTO password) {
        try {
            Jws<Claims> jwsToken = Token.parseJwt(token);
            String tokenStr=jwsToken.toString();
            int indexStart = tokenStr.indexOf("email") + "email".length()+1;
            int indexEnd = tokenStr.indexOf(",", indexStart);
            String email = tokenStr.substring(indexStart, indexEnd);
            EntityUser entityUser = userService.findByEmail(email);
            userService.updatePassword(entityUser, password.password());
            return new ResponseEntity<>(tokenStr, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(token, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}

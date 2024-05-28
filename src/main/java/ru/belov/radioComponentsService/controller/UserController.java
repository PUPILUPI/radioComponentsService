package ru.belov.radioComponentsService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.belov.radioComponentsService.domain.dto.sql.UserDTO;
import ru.belov.radioComponentsService.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "test";

    }


    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    @PostMapping("/registration")
    public @ResponseBody ResponseEntity<UserDTO> registration(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.create(userDTO));
    }

//    @GetMapping("/token/{token}")
//    public @ResponseBody ResponseEntity token( @PathVariable String token) {
//        try {
//            Jws<Claims> jwsToken = Token.parseJwt(token);
//            String tokenStr=jwsToken.toString();
//            int indexStart = tokenStr.indexOf("email") + "email".length()+1;
//            int indexEnd = tokenStr.indexOf(",", indexStart);
//            String email = tokenStr.substring(indexStart, indexEnd);
//            EntityUser entityUser = userService.findByEmail(email);
//            userService.updateSubmitFlagUser(entityUser);
//            return new ResponseEntity<>(tokenStr, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(token, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PostMapping("/recovery")
//    public @ResponseBody ResponseEntity recovery(@RequestBody EmailDTO email) {
//        try {
//            emailService.sendEmailRecover(email.email());
//        } catch (MailException mailException) {
//            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
//    }

//    @PostMapping("/recovery/{token}")
//    public @ResponseBody ResponseEntity recovery(@PathVariable String token, @RequestBody PasswordDTO password) {
//        try {
//            Jws<Claims> jwsToken = Token.parseJwt(token);
//            String tokenStr=jwsToken.toString();
//            int indexStart = tokenStr.indexOf("email") + "email".length()+1;
//            int indexEnd = tokenStr.indexOf(",", indexStart);
//            String email = tokenStr.substring(indexStart, indexEnd);
//            EntityUser entityUser = userService.findByEmail(email);
//            userService.updatePassword(entityUser, password.password());
//            return new ResponseEntity<>(tokenStr, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(token, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}

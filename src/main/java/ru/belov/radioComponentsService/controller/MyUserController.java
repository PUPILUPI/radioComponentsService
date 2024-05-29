package ru.belov.radioComponentsService.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.dto.sql.UserDTO;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.jwt.Token;
import ru.belov.radioComponentsService.service.UserService;

@Controller
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api")
public class MyUserController {
    private final UserService userService;


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
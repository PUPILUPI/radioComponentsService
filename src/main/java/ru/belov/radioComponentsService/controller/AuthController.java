package ru.belov.radioComponentsService.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.RefreshDto;
import ru.belov.radioComponentsService.domain.dto.JwtTokenDtoRes;
import ru.belov.radioComponentsService.domain.dto.sql.EmailDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.LoginDtoReq;
import ru.belov.radioComponentsService.domain.dto.sql.PasswordDTOReq;
import ru.belov.radioComponentsService.domain.dto.sql.RegDtoReq;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.exceptions.GeneralException;
import ru.belov.radioComponentsService.jwt.Token;
import ru.belov.radioComponentsService.mapper.UserMapper;
import ru.belov.radioComponentsService.security.CustomUserDetails;
import ru.belov.radioComponentsService.service.AuthService;
import ru.belov.radioComponentsService.service.EmailService;
import ru.belov.radioComponentsService.service.UserService;
import ru.belov.radioComponentsService.utils.JwtUtil;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Authorization API", description = "")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final EmailService emailService;

    @Operation(summary = "Register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/register")
    public ResponseEntity<RegDtoReq> registerUser(@RequestBody @Valid RegDtoReq req) {
        MyUser user = userService.create(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toDTO(user));
    }

    @GetMapping("/confirm/{token}")
    public ResponseEntity<String> confirm(@PathVariable String token) {
        Jws<Claims> jwsToken = Token.parseJwt(token);
        String tokenStr = jwsToken.toString();
        int indexStart = tokenStr.indexOf("email") + "email".length() + 1;
        int indexEnd = tokenStr.indexOf(",", indexStart);
        String email = tokenStr.substring(indexStart, indexEnd);
        MyUser user = userService.findByEmail(email);
        userService.updateSubmitFlagUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("Почта успешно подтвеждена");
    }

    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtTokenDtoRes.class))
                    })
    })
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDtoRes> login(@RequestBody @Valid LoginDtoReq req) {
        MyUser user = authService.login(req);
        JwtTokenDtoRes res = new JwtTokenDtoRes();
        res.setAccess(JwtUtil.generateAccessToken(user));
        res.setRefresh(JwtUtil.generateRefreshToken(user));
        res.setRole(user.getUserRole());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @PostMapping("/recover")
    public @ResponseBody ResponseEntity<String> recover(@RequestBody @Valid EmailDTOReq req) {
        try {
            emailService.recoverPasswordEmail(req.email());
        } catch (MailException mailException) {
            throw new GeneralException(550, "Unable to send email");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Please check your inbox");
    }

    @PostMapping("/recover/{token}")
    public @ResponseBody ResponseEntity<String> recover(@PathVariable String token, @RequestBody @Valid PasswordDTOReq req) {
            Jws<Claims> jwsToken = Token.parseJwt(token);
            String tokenStr=jwsToken.toString();
            int indexStart = tokenStr.indexOf("email") + "email".length()+1;
            int indexEnd = tokenStr.indexOf(",", indexStart);
            String email = tokenStr.substring(indexStart, indexEnd);
            MyUser user = userService.findByEmail(email);
            userService.updatePassword(user.getUserId(), req.password());
            return ResponseEntity.status(HttpStatus.OK).body("пароль успешно изменен");
    }

    @Operation(summary = "Refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtTokenDtoRes.class))
                    })
    })
    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenDtoRes> refresh(@RequestBody @Valid RefreshDto req) {
        JwtTokenDtoRes res = authService.refresh(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @PostMapping("/update")
    @Secured("ROLE_AUTH")
    public ResponseEntity<String> update(@RequestBody @Valid PasswordDTOReq req,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        userService.updatePassword(customUserDetails.getUser().getUserId(), req.password());
        return ResponseEntity.status(HttpStatus.OK).body("пароль успешно изменен");
    }
}

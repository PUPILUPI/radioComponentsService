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
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.belov.radioComponentsService.domain.RefreshDto;
import ru.belov.radioComponentsService.domain.dto.JwtTokenDtoRes;
import ru.belov.radioComponentsService.domain.dto.LoginDtoReq;
import ru.belov.radioComponentsService.domain.dto.sql.UserDTO;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.jwt.Token;
import ru.belov.radioComponentsService.mapper.UserMapper;
import ru.belov.radioComponentsService.service.AuthService;
import ru.belov.radioComponentsService.service.UserService;
import ru.belov.radioComponentsService.utils.JwtUtil;

/**
 * @author Vladimir Krasnov
 */
@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Authorization API", description = "")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;
    @Operation(summary = "Register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        MyUser user = userService.create(userDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userMapper.toDTO(user));
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<String> token( @PathVariable String token) {
        try {
            Jws<Claims> jwsToken = Token.parseJwt(token);
            String tokenStr=jwsToken.toString();
            int indexStart = tokenStr.indexOf("email") + "email".length()+1;
            int indexEnd = tokenStr.indexOf(",", indexStart);
            String email = tokenStr.substring(indexStart, indexEnd);
            MyUser entityUser = userService.findByEmail(email);
            userService.updateSubmitFlagUser(entityUser);
            return new ResponseEntity<>(tokenStr, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(token, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<JwtTokenDtoRes> login(@RequestBody @Valid LoginDtoReq req){
        MyUser user = authService.login(req);
        JwtTokenDtoRes res = new JwtTokenDtoRes();
        res.setAccess(JwtUtil.generateAccessToken(user));
        res.setRefresh(JwtUtil.generateRefreshToken(user));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
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
    public ResponseEntity<JwtTokenDtoRes> refresh(@RequestBody @Valid RefreshDto req){
        JwtTokenDtoRes res = authService.refresh(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }
}

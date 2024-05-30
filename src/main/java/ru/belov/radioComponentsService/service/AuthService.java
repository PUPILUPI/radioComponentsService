package ru.belov.radioComponentsService.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.radioComponentsService.domain.RefreshDto;
import ru.belov.radioComponentsService.domain.dto.JwtTokenDtoRes;
import ru.belov.radioComponentsService.domain.dto.LoginDtoReq;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.exceptions.GeneralException;
import ru.belov.radioComponentsService.utils.JwtUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vladimir Krasnov
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder encoder;

    public MyUser login(LoginDtoReq req){
        Optional<MyUser> user = userService.findByEmail(req.getEmail());
        if(user.isEmpty()) {
            throw new GeneralException(404, "пользователь с таким email не найден");
        }
        if(!user.get().getSubmitFlag()) {
            throw new GeneralException(409, "Почта не подтверждена, Пожалуйста, подветрдите почту перед входом");
        }
        if(!encoder.matches(req.getPassword(), user.get().getPassword())) {
            throw new GeneralException(409, "Неверный пароль");
        }

        return userService.findByEmail(req.getEmail()).get();
    }

    public JwtTokenDtoRes refresh(RefreshDto req){
        Jws<Claims> claims = JwtUtil.getClaims(req.getRefresh());
        if(!claims.getBody().get("tokenType").toString().equals("refresh")){
            throw new RuntimeException("adjokadslj;");
        }
        String userId = claims.getBody().get("sub").toString();
        MyUser user = userService.getById(Long.parseLong(userId));
        JwtTokenDtoRes res = JwtTokenDtoRes.builder()
                .access(JwtUtil.generateAccessToken(user))
                .refresh(JwtUtil.generateRefreshToken(user))
                .build();
        return res;
    }

}

package ru.belov.radioComponentsService.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.radioComponentsService.domain.RefreshDto;
import ru.belov.radioComponentsService.domain.dto.JwtTokenDtoRes;
import ru.belov.radioComponentsService.domain.dto.LoginDtoReq;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.utils.JwtUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author Vladimir Krasnov
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    public MyUser login(LoginDtoReq req){
        //TODO цепочку проверок
        return userService.findByEmail(req.getEmail());
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

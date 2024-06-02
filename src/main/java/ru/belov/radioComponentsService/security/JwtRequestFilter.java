package ru.belov.radioComponentsService.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.service.UserService;
import ru.belov.radioComponentsService.utils.JwtUtil;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        Long userId = null;
        String jwt = null;
        String tokenType = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                Claims claims = JwtUtil.getClaims(jwt).getBody();
                tokenType = claims.get("tokenType").toString();
                if(tokenType.equals("access")){
                    userId = Long.parseLong(claims.get("sub").toString());
                }

            } catch (ExpiredJwtException e) {
                System.out.println("Token timeout");
            } catch (Exception e) {
                System.out.println("Wrong token");
            }
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            MyUser user = userService.getById(userId);
            //мапишь роли
            List<SimpleGrantedAuthority> authorities = mapAuthorities(user);
            //----
            UserDetails userDetails = new CustomUserDetails(user, authorities);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

    private List<SimpleGrantedAuthority> mapAuthorities(MyUser user){
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getUserRole()),
                new SimpleGrantedAuthority("ROLE_AUTH"));
    }
}

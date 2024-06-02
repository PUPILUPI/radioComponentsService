package ru.belov.radioComponentsService.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
public class Token {
    private final static long time = 60 * 24 * 60 * 1000;
    private final static String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";


    public static String getJwt(String email) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        Instant now = Instant.now();
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + time;
        Date exp = new Date(expMillis);
        String jwt = Jwts.builder()
                .claim("email", email)
                .setSubject("user")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(exp)
                .signWith(hmacKey)
                .compact();
        return jwt;
    }

    public static Jws<Claims> parseJwt(String token) throws SignatureException {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        Jws<Claims> jwt;
            jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(token);
        return jwt;
    }
}
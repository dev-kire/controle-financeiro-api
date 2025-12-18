package com.dev_ki.controle_financeiro_api.infra.security;

import com.dev_ki.controle_financeiro_api.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration}")
    private Long expiration;

    public String gerarToken(User user) {

        Instant agora = Instant.now();
        Instant expiracao = agora.plus(expiration, ChronoUnit.HOURS);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(Date.from(agora))
                .setExpiration(Date.from(expiracao))
                .signWith(
                        Keys.hmacShaKeyFor(secret.getBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public String validarToken(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}

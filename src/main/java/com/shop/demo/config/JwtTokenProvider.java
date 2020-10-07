package com.shop.demo.config;

import com.shop.demo.accounts.service.command.AccountService;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final int ONE_HOUR_TO_SECOND = 3600000;
    private final AccountService accountService;
    @Value("${jwt.secret}")
    private String secret;
    private String key;

    @PostConstruct
    protected void init() {
        this.key = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String email) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + ONE_HOUR_TO_SECOND);

        return Jwts.builder()
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}

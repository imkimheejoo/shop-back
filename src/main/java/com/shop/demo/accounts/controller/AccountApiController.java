package com.shop.demo.accounts.controller;

import com.shop.demo.accounts.domain.Account;
import com.shop.demo.accounts.dto.LoginRequestDto;
import com.shop.demo.accounts.service.command.AccountService;
import com.shop.demo.config.JwtAccessToken;
import com.shop.demo.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        Account account = accountService.authenticate(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        String accessToken = jwtTokenProvider.createToken(account.getEmail());

        return new ResponseEntity(JwtAccessToken.builder().accessToken(accessToken).build(), HttpStatus.OK);
    }
}

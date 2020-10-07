package com.shop.demo.config;

import com.shop.demo.accounts.domain.Account;
import com.shop.demo.accounts.domain.AccountRole;
import com.shop.demo.accounts.dto.SignUpRequestDto;
import com.shop.demo.accounts.service.command.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationRunnerConfig implements ApplicationRunner {

    private final AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("email@email.com", "password", "김이름");
        Account account = accountService.register(signUpRequestDto, AccountRole.CUSTOMER);
    }
}

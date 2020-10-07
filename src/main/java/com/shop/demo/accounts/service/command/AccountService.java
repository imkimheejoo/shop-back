package com.shop.demo.accounts.service.command;

import com.shop.demo.accounts.domain.Account;
import com.shop.demo.accounts.domain.AccountRole;
import com.shop.demo.accounts.dto.SignUpRequestDto;
import com.shop.demo.accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account register(SignUpRequestDto signUpRequestDto, AccountRole role) {
        Account newAccount = Account.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .name(signUpRequestDto.getName())
                .accountRole(role)
                .build();

        return accountRepository.save(newAccount);
    }
}

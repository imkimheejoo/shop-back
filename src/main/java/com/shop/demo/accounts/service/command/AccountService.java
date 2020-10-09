package com.shop.demo.accounts.service.command;

import com.shop.demo.accounts.domain.Account;
import com.shop.demo.accounts.domain.AccountRole;
import com.shop.demo.accounts.dto.SignUpRequestDto;
import com.shop.demo.accounts.repository.AccountRepository;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account register(SignUpRequestDto signUpRequestDto, AccountRole role) {
        Account newAccount = Account.builder()
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .name(signUpRequestDto.getName())
                .role(role)
                .build();

        return accountRepository.save(newAccount);
    }

    @Transactional(readOnly = true)
    public Account authenticate(String email, String password) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundDataException("회원을 찾을 수 없습니다.", ErrorCode.INVALID_TOKEN));

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new NotFoundDataException("회원을 찾을 수 없습니다.", ErrorCode.INVALID_TOKEN);
        }

        return account;
    }
}

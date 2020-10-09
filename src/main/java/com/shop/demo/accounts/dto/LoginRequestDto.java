package com.shop.demo.accounts.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}

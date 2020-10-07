package com.shop.demo.accounts.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class LoggedInAccount extends User {
    private Long id;
    private String name;

    @Builder(builderMethodName = "accountBuilder")
    public LoggedInAccount(String email, String password, Long id, String name, AccountRole role) {
        super(email, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
        this.id = id;
        this.name = name;
    }

    public String getEmail() {
        return this.getUsername();
    }

}

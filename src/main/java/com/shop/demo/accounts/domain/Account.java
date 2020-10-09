package com.shop.demo.accounts.domain;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @Builder
    public Account(String email, String password, String name, AccountRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public UserDetails toUserDetails() {
        return LoggedInAccount.accountBuilder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .role(role)
                .build();
    }

}

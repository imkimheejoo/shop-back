package com.shop.demo.accounts.domain;

import com.shop.demo.accounts.dto.SignUpRequestDto;
import lombok.*;

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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private AccountRole accountRole;

    @Builder
    public Account(String email, String password, String name, AccountRole accountRole) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.accountRole = accountRole;
    }
}

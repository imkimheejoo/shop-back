package com.shop.demo.common;

import com.shop.demo.accounts.domain.AccountRole;
import com.shop.demo.accounts.domain.LoggedInAccount;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {
        long id = Optional.of(withAccount.id())
                .orElseThrow(() -> new IllegalArgumentException(withAccount + " id는 null을 허용하지 않습니다."));

        List<GrantedAuthority> grantedAuthorities = Arrays.stream(withAccount.authorities())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        if (grantedAuthorities.isEmpty()) {
            for (String role : withAccount.roles()) {
                if (role.startsWith(ROLE_PREFIX)) {
                    throw new IllegalArgumentException("roles 의 값은 ROLE_로 시작할 수 없습니다.");
                }
                grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
            }
        }

        if (withAccount.roles().length != 1) {
            throw new IllegalArgumentException("권한은 반드시 1개여야 합니다.");
        }

        AccountRole role = AccountRole.valueOf(withAccount.roles()[0]);

        User principal = LoggedInAccount.accountBuilder()
                .id(id)
                .name(withAccount.name())
                .email(withAccount.email())
                .password(withAccount.password())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}


package com.tw.tradeaway.security;

import java.util.Arrays;

import com.tw.tradeaway.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getAuthority())),
                true,
                null
        );
    }

}


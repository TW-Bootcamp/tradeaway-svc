package com.tw.tradeaway.service.token;

import com.tw.tradeaway.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserVerificationTokenService {

    private static String TOKEN_SALT = "d7 60 26 52 11 44 fd f6 c6 21 80 d7 ae 09 c2 80 c8 2d d3 e8";

    public   String generate(User user){
        String input = TOKEN_SALT+user.getEmail()+user.getUsername();
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    public boolean validate(User user, String token){
        return (token.equals(this.generate(user)));
    }
}

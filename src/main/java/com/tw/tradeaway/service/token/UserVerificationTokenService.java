package com.tw.tradeaway.service.token;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.security.JwtUser;
import com.tw.tradeaway.security.JwtUserFactory;
import com.tw.tradeaway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserVerificationTokenService {

    @Autowired
    private UserService userService;


    private final static String TOKEN_SALT = "d7 60 26 52 11 44 fd f6 c6 21 80 d7 ae 09 c2 80 c8 2d d3 e8";

    public String generate(User user){
        JwtUser jwtUser = JwtUserFactory.create(user);
        return this.generate(jwtUser);
    }

    public   String generate(JwtUser user){
        String input = TOKEN_SALT+user.getEmail()+user.getUsername();
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    public boolean validate(JwtUser user, String token){
        if(token.equals(this.generate(user))){
            userService.verifyEmail(user.getUsername());
            return true;
        }
        return false;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

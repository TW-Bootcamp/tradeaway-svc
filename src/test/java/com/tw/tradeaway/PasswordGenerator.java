package com.tw.tradeaway;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("superadmin123:"+encoder.encode("superadmin123"));
        System.out.println("admin123:"+encoder.encode("admin123"));

    }
}

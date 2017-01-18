package com.tw.tradeaway.security;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final String authority;

    public JwtAuthenticationResponse() {
        this.token = null;
        this.authority = null;
    }

    public JwtAuthenticationResponse(String token, String authority) {
        this.token = token;
        this.authority = authority;
    }

    public String getToken() {
        return this.token;
    }

    public String getAuthority() {
        return authority;
    }
}


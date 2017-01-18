package com.tw.tradeaway.controller;

import com.tw.tradeaway.security.JwtAuthenticationRequest;
import com.tw.tradeaway.security.JwtAuthenticationResponse;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ControllerTestUtils {

    private static final String BASE_PATH = "http://localhost";

    public static String getBasePath(int port){
        return BASE_PATH + ":"+ port;
    }

    public static String getAuthUrl(int port){
        return getBasePath(port) + "/auth";
    }

    public static HttpHeaders getUserAuthenticationHeaders(String authUrl,
                                                           String username,
                                                           String password){
        TestRestTemplate restTemplate = new TestRestTemplate();
        JwtAuthenticationRequest req = new JwtAuthenticationRequest();
        req.setUsername(username);
        req.setPassword(password);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate
                .postForEntity(authUrl, req, JwtAuthenticationResponse.class);
        String token = responseEntity.getBody().getToken();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization",token);
        return requestHeaders;
    }
}

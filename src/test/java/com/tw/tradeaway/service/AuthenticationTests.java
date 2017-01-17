package com.tw.tradeaway.service;

import com.tw.tradeaway.Application;
import com.tw.tradeaway.security.JwtAuthenticationRequest;
import com.tw.tradeaway.security.JwtAuthenticationResponse;
import com.tw.tradeaway.security.JwtUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthenticationTests {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void authenticate()
    {
        TestRestTemplate restTemplate = new TestRestTemplate();
        JwtAuthenticationRequest req = new JwtAuthenticationRequest();
        req.setUsername("admin");
        req.setPassword("admin123");

        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/auth", req, JwtAuthenticationResponse.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        assertNotNull(responseEntity.getBody().getToken());
    }


    @Test
    public void getLoginUser()
    {
        TestRestTemplate restTemplate = new TestRestTemplate();
        JwtAuthenticationRequest req = new JwtAuthenticationRequest();
        req.setUsername("superadmin");
        req.setPassword("superadmin123");

        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/auth", req, JwtAuthenticationResponse.class);
        String token = responseEntity.getBody().getToken();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization",token);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(null,requestHeaders);


        ResponseEntity<String> jwtUserResponseEntity = restTemplate
                .exchange("http://localhost:" + port + "/user", HttpMethod.GET, requestEntity, String.class);

        assertNotNull(jwtUserResponseEntity);
        assertNotNull(jwtUserResponseEntity.getBody());
        assertTrue(jwtUserResponseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(jwtUserResponseEntity.getBody().contains("admin1@gmail.com"));
    }

}
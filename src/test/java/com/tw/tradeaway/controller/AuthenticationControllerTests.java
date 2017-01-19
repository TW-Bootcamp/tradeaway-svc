package com.tw.tradeaway.controller;

import com.tw.tradeaway.Application;
import com.tw.tradeaway.security.JwtAuthenticationRequest;
import com.tw.tradeaway.security.JwtAuthenticationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static com.tw.tradeaway.controller.ControllerTestUtils.getAuthUrl;
import static com.tw.tradeaway.controller.ControllerTestUtils.getUserAuthenticationHeaders;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthenticationControllerTests {

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
        HttpHeaders httpHeaders =
                getUserAuthenticationHeaders(getAuthUrl(port),
                        "superadmin",
                        "superadmin123");
        HttpEntity<?> requestEntity = new HttpEntity<Object>(null,httpHeaders);

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> jwtUserResponseEntity = restTemplate
                .exchange("http://localhost:" + port + "/user", HttpMethod.GET, requestEntity, String.class);

        assertNotNull(jwtUserResponseEntity);
        assertNotNull(jwtUserResponseEntity.getBody());
        assertTrue(jwtUserResponseEntity.getStatusCode() == HttpStatus.OK);
        //assertTrue(jwtUserResponseEntity.getBody().contains("admin1@gmail.com"));
    }

}

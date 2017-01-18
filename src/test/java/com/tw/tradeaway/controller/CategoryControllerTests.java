package com.tw.tradeaway.controller;

import com.tw.tradeaway.Application;
import com.tw.tradeaway.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.tw.tradeaway.controller.ControllerTestUtils.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CategoryControllerTests {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void shouldFetchCategoriesList() throws Exception {

        HttpHeaders httpHeaders =
                getUserAuthenticationHeaders(getAuthUrl(port),
                        "admin",
                        "admin123");

        HttpEntity<?> requestEntity = new HttpEntity<Object>(null,httpHeaders);

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<Category[]> categoriesResponseEntity = restTemplate
                .exchange(
                        getBasePath(port) + "/categories",
                        HttpMethod.GET,
                        requestEntity,
                        Category[].class);

        assertNotNull(categoriesResponseEntity);
        assertNotNull(categoriesResponseEntity.getBody());
        assertTrue(categoriesResponseEntity.getBody().length > 0);
    }
}

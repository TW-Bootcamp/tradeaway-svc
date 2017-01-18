package com.tw.tradeaway.service.token;

import com.tw.tradeaway.domain.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserVerificationTokenServiceTest {
    @Test
    public void shouldGenerateMD5Token() throws Exception {
        UserVerificationTokenService tokenService = new UserVerificationTokenService();
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@email.com");

        String generatedToken=tokenService.generate(user);

        assertThat(generatedToken).isEqualTo("349870f9661b48adc7c3dbb0675d8a74");
    }


    @Test
    public void shouldBeAbleToValidateGeneratedToken() throws Exception {
        UserVerificationTokenService tokenService = new UserVerificationTokenService();
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@email.com");
        String generatedToken=tokenService.generate(user);

        boolean validateToken = tokenService.validate(user,generatedToken);

        assertThat(validateToken).isTrue();
    }
}

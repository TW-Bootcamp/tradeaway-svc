package com.tw.tradeaway.service.token;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.security.JwtUser;
import com.tw.tradeaway.security.JwtUserFactory;
import com.tw.tradeaway.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserVerificationTokenServiceTest {

    private UserVerificationTokenService tokenService;
    private User user;
    private JwtUser jwtUser;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        when(userService.verifyEmail("testuser")).thenReturn(new User());
        tokenService = new UserVerificationTokenService();
        tokenService.setUserService(userService);
        user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@email.com");
        user.setAuthority("ROLE_TEST");
        jwtUser = JwtUserFactory.create(user);
    }

    @Test
    public void shouldGenerateMD5Token() throws Exception {

        String generatedToken= tokenService.generate(jwtUser);

        assertThat(generatedToken).isEqualTo("349870f9661b48adc7c3dbb0675d8a74");
    }


    @Test
    public void shouldBeAbleToValidateGeneratedToken() throws Exception {

        String generatedToken=tokenService.generate(user);

        boolean validateToken = tokenService.validate(jwtUser,generatedToken);

        assertThat(validateToken).isTrue();
    }
}

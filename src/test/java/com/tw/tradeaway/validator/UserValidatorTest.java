package com.tw.tradeaway.validator;

import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

  @Mock
  private UserService userService;

  private UserValidator userValidator = new UserValidator();

  private User user;

  @Before
  public void setUp() throws Exception {
    userValidator.setService(userService);
    user = new User();
    userValidator.setUser(user);
  }

  @Test
  public void verifyUserWithUserNameExists() throws Exception {
    when(userService.findByUsername(user.getUsername())).thenReturn(new User());

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(true);

    assertThat("Username already Exists.!!!").isEqualTo(userValidator.getErrorMessage());
  }

  @Test
  public void verifyUserWithEmailExists() throws Exception {
    when(userService.findByEmail(user.getEmail())).thenReturn(new User());

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(true);

    assertThat("Email already registered.!!!").isEqualTo(userValidator.getErrorMessage());
  }

  @Test
  public void verifyUserWithUserNameDoesNotExist() throws Exception {
    when(userService.findByUsername(user.getUsername())).thenReturn(null);

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(false);
  }

  @Test
  public void verifyUserWithEmailDoesNotExist() throws Exception {
    when(userService.findByEmail(user.getEmail())).thenReturn(null);

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(false);
  }
}
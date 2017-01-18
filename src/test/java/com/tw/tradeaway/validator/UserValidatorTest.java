package com.tw.tradeaway.validator;

import com.tw.tradeaway.domain.Buyer;
import com.tw.tradeaway.domain.User;
import com.tw.tradeaway.repository.BuyerRepository;
import com.tw.tradeaway.request.UserRequest;
import com.tw.tradeaway.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

  @Mock
  private UserService userService;

  private UserValidator userValidator = new UserValidator();

  private UserRequest userRequest;

  @Before
  public void setUp() throws Exception {
    userValidator.setService(userService);
    userRequest = new UserRequest();
    userValidator.setUser(userRequest);
  }

  @Test
  public void verifyUserWithUserNameExists() throws Exception {
    when(userService.findByUsername(userRequest.getUsername())).thenReturn(new User());

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(true);

    assertThat("Username already Exists.!!!").isEqualTo(userValidator.getErrorMessage());
  }

  @Test
  public void verifyUserWithEmailExists() throws Exception {


       when(userService.findByEmail(userRequest.getEmail(), userRequest.getAuthority())).thenReturn(true);

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(true);

    assertThat("Email already registered.!!!").isEqualTo(userValidator.getErrorMessage());
  }


  @Test
  public void verifyUserWithUserNameDoesNotExist() throws Exception {
    when(userService.findByUsername(userRequest.getUsername())).thenReturn(null);

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(false);
  }


  @Test
  public void verifyUserWithEmailDoesNotExist() throws Exception {
    when(userService.findByEmail(userRequest.getEmail() ,userRequest.getAuthority())).thenReturn(false);

    boolean actualValue = userValidator.isExistingUser();

    assertThat(actualValue).isEqualTo(false);
  }

}
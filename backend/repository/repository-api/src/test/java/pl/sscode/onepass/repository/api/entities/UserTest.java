package pl.sscode.onepass.repository.api.entities;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by sscode on 2017-06-15.
 */
public class UserTest {

    public static final String USERNAME = "useruser";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email@email.com";

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldNotHaveViolations() throws Exception {
        User user = setupAccount();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isEmpty();
    }

    @Test
    public void shouldViolateUserNameSizeMin() throws Exception {
        User user = setupAccount();
        user.setUsername("aa");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Username length should be between 3 and 30");
    }

    @Test
    public void shouldViolateUserNameSizeMax() throws Exception {
        User user = setupAccount();
        user.setUsername("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Username length should be between 3 and 30");
    }

    @Test
    public void shouldViolateUsernameNotValidChars() throws Exception {
        User user = setupAccount();
        user.setUsername("@#$%^&&*((^%$");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Username must match \"^[A-Za-z0-9+_.-]+\"");
    }

    @Test
    public void shouldViolateEmailSizeMax() throws Exception {
        User user = setupAccount();
        user.setEmail("email@email.coooooooooooooooooooooooooooooooooooooooooooooooom");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Email length should be between 10 and 40");
    }

    @Test
    public void shouldViolateEmailNotValidChars() throws Exception {
        User user = setupAccount();
        user.setEmail("@$$^*(*$^&");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Email must match \"^[A-Za-z0-9+_.-]+@(.+)$\"");
    }

    @Test
    public void shouldViolatePasswordSizeMin() throws Exception {
        User user = setupAccount();
        user.setPassword("aaa");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Password length should be between 8 and 40");
    }

    @Test
    public void shouldViolatePasswordSizeMax() throws Exception {
        User user = setupAccount();
        user.setPassword("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Password length should be between 8 and 40");
    }

    private User setupAccount() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        return user;
    }
}

package pl.sscode.onepass.repository.impl.convert.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.repository.impl.convert.user.UserConverterImpl;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by sscode on 2017-06-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserConvertImplTest {

    @InjectMocks
    UserConverterImpl userConverter;

    @Test
    public void shouldConvertTo() {
        User user = setupUser();

        UserDto userDto = userConverter.convertTo(user);

        assertThat(userDto.getId()).isEqualTo(user.getId());
        assertThat(userDto.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDto.getPassword()).isEqualTo(user.getPassword());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void shouldConvertFrom() {
        UserDto userDto = setupUserDto();

        User user = userConverter.convertFrom(userDto);

        assertThat(user.getId()).isEqualTo(userDto.getId());
        assertThat(user.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(user.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
    }


    private UserDto setupUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("useruser");
        userDto.setEmail("loopData@loopData.com");
        userDto.setPassword("passwordpass");
        return userDto;
    }


    private User setupUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("useruser");
        user.setEmail("loopData@loopData.com");
        user.setPassword("passwordpass");
        return user;
    }
}

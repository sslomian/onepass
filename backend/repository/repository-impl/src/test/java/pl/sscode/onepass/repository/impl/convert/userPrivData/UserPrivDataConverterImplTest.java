package pl.sscode.onepass.repository.impl.convert.userPrivData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.dto.UserPrivDataDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.entities.UserPrivData;
import pl.sscode.onepass.repository.api.repository.api.converter.Converter;
import pl.sscode.onepass.repository.api.repository.impl.convert.userPrivData.UserPrivDataConverterImpl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sscode on 2017-08-22.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserPrivDataConverterImplTest {

    @InjectMocks
    UserPrivDataConverterImpl userPrivDataConverter;

    @Mock
    Converter<User, UserDto> converter;

    @Test
    public void shouldConvertTo() throws Exception {
        UserPrivData userPrivData = setupUserPrivData();

        UserDto userDto = mock(UserDto.class);

        when(converter.convertTo(userPrivData.getUser())).thenReturn(userDto);

        UserPrivDataDto userPrivDataDto = userPrivDataConverter.convertTo(userPrivData);

        assertThat(userPrivData.getId()).isEqualTo(userPrivDataDto.getId());
        assertThat(userPrivData.getUsernameOrEmail()).isEqualTo(userPrivDataDto.getUsernameOrEmail());
        assertThat(userPrivData.getPassword()).isEqualTo(userPrivDataDto.getPassword());
        assertThat(userPrivData.getSite()).isEqualTo(userPrivDataDto.getSite());
        assertThat(userPrivData.getDescription()).isEqualTo(userPrivDataDto.getDescription());

        verify(converter).convertTo(userPrivData.getUser());
    }

    @Test
    public void shouldConfertFrom() throws Exception {
        UserPrivDataDto userPrivDataDto = setupUserPrivDataDto();

        User user = mock(User.class);

        when(converter.convertFrom(userPrivDataDto.getUser())).thenReturn(user);

        UserPrivData userPrivData = userPrivDataConverter.convertFrom(userPrivDataDto);

        assertThat(userPrivDataDto.getId()).isEqualTo(userPrivData.getId());
        assertThat(userPrivDataDto.getUsernameOrEmail()).isEqualTo(userPrivData.getUsernameOrEmail());
        assertThat(userPrivDataDto.getPassword()).isEqualTo(userPrivData.getPassword());
        assertThat(userPrivDataDto.getSite()).isEqualTo(userPrivData.getSite());
        assertThat(userPrivDataDto.getDescription()).isEqualTo(userPrivData.getDescription());

        verify(converter).convertFrom(userPrivDataDto.getUser());
    }


    private UserPrivData setupUserPrivData() {
        UserPrivData userPrivData = new UserPrivData();
        User user = mock(User.class);
        userPrivData.setId(1L);
        userPrivData.setUsernameOrEmail("useruser");
        userPrivData.setPassword("password");
        userPrivData.setSite("test.com");
        userPrivData.setDescription("This is description");
        userPrivData.setUser(user);
        return userPrivData;
    }

    private UserPrivDataDto setupUserPrivDataDto() {
        UserPrivDataDto userPrivDataDto = new UserPrivDataDto();
        UserDto user = mock(UserDto.class);
        userPrivDataDto.setId(1L);
        userPrivDataDto.setUsernameOrEmail("useruser");
        userPrivDataDto.setPassword("password");
        userPrivDataDto.setSite("test.com");
        userPrivDataDto.setDescription("This is description");
        userPrivDataDto.setUser(user);
        return userPrivDataDto;
    }

}

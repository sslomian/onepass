package pl.sscode.onepass.repository.impl.service.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.repository.api.converter.Converter;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserRepository;
import pl.sscode.onepass.repository.api.repository.impl.service.user.UserRepositoryServiceImpl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sscode on 2017-06-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryServiceImplTest {

    private static final String USERNAME = "useruser";
    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "passwordpass";

    @InjectMocks
    private UserRepositoryServiceImpl userRepositoryService;

    @Mock
    private Converter<User, UserDto> converter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldSaveAccount() throws Exception {
        //given
        UserDto dto = new UserDto();
        dto.setId(1L);
        dto.setUsername(USERNAME);
        dto.setEmail(EMAIL);
        dto.setPassword(PASSWORD);
        User entity = mock(User.class);

        when(passwordEncoder.encode(dto.getPassword())).thenReturn(PASSWORD);
        when(converter.convertFrom(dto)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);
        when(converter.convertTo(entity)).thenReturn(dto);

        //when
        UserDto saved = userRepositoryService.save(dto);

        //then
        verify(passwordEncoder).encode(PASSWORD);
        verify(converter).convertFrom(dto);
        verify(userRepository).save(entity);
        verify(converter).convertTo(entity);
        assertThat(saved).isEqualTo(dto);

    }

    @Test
    public void shouldFindByName() throws Exception {
        //given
        UserDto dto = mock(UserDto.class);
        User entity = mock(User.class);

        when(userRepository.findByUsername(USERNAME)).thenReturn(entity);
        when(converter.convertTo(entity)).thenReturn(dto);

        //when
        UserDto found = userRepositoryService.findByUsername(USERNAME);

        //then
        verify(converter).convertTo(entity);
        assertThat(found).isEqualTo(dto);

    }

    @Test
    public void shouldFindByEmail() throws Exception {
        UserDto dto = mock(UserDto.class);
        User entity = mock(User.class);

        when(userRepository.findByEmail(EMAIL)).thenReturn(entity);
        when(converter.convertTo(entity)).thenReturn(dto);

        UserDto found = userRepositoryService.findByEmail(EMAIL);

        verify(converter).convertTo(entity);
        assertThat(found).isEqualTo(dto);
    }


}

package pl.sscode.onepass.repository.impl.service.userPrivData;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.dto.UserPrivDataDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.entities.UserPrivData;
import pl.sscode.onepass.repository.api.repository.api.converter.Converter;
import pl.sscode.onepass.repository.api.repository.impl.convert.user.UserConverterImpl;
import pl.sscode.onepass.repository.api.repository.impl.convert.userPrivData.UserPrivDataConverterImpl;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserPrivDataRepository;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserRepository;
import pl.sscode.onepass.repository.api.repository.impl.service.userPrivData.UserPrivDataRepositoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sscode on 2017-07-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserPrivDataRepositoryServiceImplTest {

    private static final String USERNAME = "useruser";
    private static final String SITE = "test.com";
    private static final String PASSWORD = "passwordpass";
    private static final String DESCRIPTION = "This is description";

    @InjectMocks
    private UserPrivDataRepositoryServiceImpl userPrivDataRepositoryService;

    @Mock
    private UserPrivDataConverterImpl userPrivDataConverter;

    @Mock
    private UserConverterImpl userConverter;

    @Mock
    private UserPrivDataRepository userPrivDataRepository;

    @Test
    public void shouldSavePrivData() throws Exception {
        //given
        UserPrivDataDto dto = new UserPrivDataDto();
        UserPrivData entity = mock(UserPrivData.class);

        dto.setId(1L);
        dto.setUsernameOrEmail(USERNAME);
        dto.setPassword(PASSWORD);
        dto.setSite(SITE);
        dto.setDescription(DESCRIPTION);

        when(userPrivDataConverter.convertFrom(dto)).thenReturn(entity);
        when(userPrivDataRepository.save(entity)).thenReturn(entity);
        when(userPrivDataConverter.convertTo(entity)).thenReturn(dto);

        //when
        UserPrivDataDto saved = userPrivDataRepositoryService.save(dto);

        //then
        verify(userPrivDataConverter).convertFrom(dto);
        verify(userPrivDataConverter).convertTo(entity);
        assertThat(saved).isEqualTo(dto);

    }

    @Test
    public void shouldFindBySite() throws Exception {
        UserPrivDataDto dto = mock(UserPrivDataDto.class);
        UserPrivData entity = mock(UserPrivData.class);

        when(userPrivDataRepository.findBySite(SITE)).thenReturn(entity);
        when(userPrivDataConverter.convertTo(entity)).thenReturn(dto);

        UserPrivDataDto found = userPrivDataRepositoryService.findBySite(SITE);

        verify(userPrivDataConverter).convertTo(entity);
        assertThat(found).isEqualTo(dto);
    }

    @Test
    public void shouldFindByUserDto() throws Exception {
        User user = mock(User.class);

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("username");
        userDto.setPassword("password");
        userDto.setEmail("email@email.com");

        UserPrivData userPrivData = mock(UserPrivData.class);

        UserPrivDataDto userPrivDataDto = mock(UserPrivDataDto.class);

        List<UserPrivData> list = new ArrayList<>();
        list.add(userPrivData);

        when(userConverter.convertFrom(userDto)).thenReturn(user);
        when(userPrivDataRepository.findByUser(user)).thenReturn(list);
        when(userPrivDataConverter.convertTo(userPrivData)).thenReturn(userPrivDataDto);

        List<UserPrivDataDto> found = userPrivDataRepositoryService.findByUserDto(userDto);

        verify(userConverter).convertFrom(userDto);
        verify(userPrivDataRepository).findByUser(user);
        verify(userPrivDataConverter).convertTo(userPrivData);

    }

}

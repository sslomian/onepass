package pl.sscode.onepass.repository.impl.service.userPrivData;

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
import pl.sscode.onepass.repository.api.repository.impl.repository.UserPrivDataRepository;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserRepository;
import pl.sscode.onepass.repository.api.repository.impl.service.userPrivData.UserPrivDataRepositoryServiceImpl;

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
    private static final String SITE = "loopData@loopData.com";
    private static final String PASSWORD = "passwordpass";
    private static final String DESCRIPTION = "This is description";

    @InjectMocks
    private UserPrivDataRepositoryServiceImpl userPrivDataRepositoryService;

    @Mock
    private Converter<UserPrivData, UserPrivDataDto> converter;

    @Mock
    private UserPrivDataRepository userPrivDataRepository;

    @Test
    public void shouldSaveAccount() throws Exception {
        //given
        UserPrivDataDto dto = new UserPrivDataDto();
        dto.setId(1L);
        dto.setUsernameOrEmail(USERNAME);
        dto.setPassword(PASSWORD);
        dto.setSite(SITE);
        dto.setDescription(DESCRIPTION);
        UserPrivData entity = mock(UserPrivData.class);

        when(converter.convertFrom(dto)).thenReturn(entity);
        when(userPrivDataRepository.save(entity)).thenReturn(entity);
        when(converter.convertTo(entity)).thenReturn(dto);

        //when
        UserPrivDataDto saved = userPrivDataRepositoryService.save(dto);

        //then
        verify(converter).convertFrom(dto);
        verify(converter).convertTo(entity);
        assertThat(saved).isEqualTo(dto);

    }
}

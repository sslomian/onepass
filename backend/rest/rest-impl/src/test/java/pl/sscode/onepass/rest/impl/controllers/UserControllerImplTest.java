package pl.sscode.onepass.rest.impl.controllers;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;
import pl.sscode.onepass.rest.api.model.ResponseDto;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by sscode on 2017-06-18.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserControllerImplTest {

    @InjectMocks
    private UserControllerImpl userController;

    @Mock
    private UserRepositoryService userRepositoryService;

    @Test
    public void shouldSave() {
        UserDto userDto = Mockito.mock(UserDto.class);

        Mockito.when(userRepositoryService.save(userDto)).thenReturn(userDto);

        ResponseDto<UserDto> saved = userController.save(userDto);

        Mockito.verify(userRepositoryService).save(userDto);
        assertThat(saved.getObject()).isEqualTo(userDto);
    }

    @Test
    public void shouldDelete() throws Exception {
        //given
        long id = 1L;

        //when
        userController.delete(id);

        //then
        Mockito.verify(userRepositoryService).delete(id);
    }

    @Test
    public void shouldFindAll() throws Exception {
        //given
        UserDto accountDto = Mockito.mock(UserDto.class);

        Mockito.when(userRepositoryService.findAll()).thenReturn(Lists.newArrayList(accountDto));

        //when
        ResponseDto<List<UserDto>> accounts = userController.findAll();

        //then
        Mockito.verify(userRepositoryService).findAll();
        assertThat(accounts.getObject()).isNotEmpty().contains(accountDto);
    }

    @Test
    public void shouldFindByUsername() throws Exception {
        //given
        UserDto userDto = Mockito.mock(UserDto.class);
        String username = "username";

        Mockito.when(userRepositoryService.findByUsername(username)).thenReturn(userDto);

        //when
        ResponseDto<UserDto> account = userController.findByUserName(username);

        //then
        Mockito.verify(userRepositoryService).findByUsername(username);
        assertThat(account.getObject()).isEqualTo(userDto);
    }

}

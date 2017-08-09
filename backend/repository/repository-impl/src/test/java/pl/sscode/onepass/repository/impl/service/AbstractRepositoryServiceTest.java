package pl.sscode.onepass.repository.impl.service;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserRepository;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sscode on 2017-06-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractRepositoryServiceTest {

    @InjectMocks
    private TestRepositoryService testRepositoryService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldSave() throws Exception {
        //given
        long id = 1L;
        UserDto dto = new UserDto();
        dto.setId(id);
        User entity = new User();
        entity.setId(id);

        when(userRepository.save(any(User.class))).thenReturn(entity);

        //when
        UserDto saved = testRepositoryService.save(dto);

        //then
        verify(userRepository).save(any(User.class));
        assertThat(saved).isEqualTo(dto);
    }

    @Test
    public void shouldGetById() throws Exception {
        //given
        long id = 1L;
        User entity = new User();
        entity.setId(id);

        when(userRepository.findOne(id)).thenReturn(entity);

        //when
        UserDto dto = testRepositoryService.getById(id);

        //then
        verify(userRepository).findOne(id);
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(id);
    }

    @Test
    public void shouldFindAll() throws Exception {
        //given
        long id = 1L;
        long id2 = 2L;
        User account = new User();
        account.setId(id);
        User account2 = new User();
        account2.setId(id2);

        when(userRepository.findAll()).thenReturn(Lists.newArrayList(account, account2));

        //when
        List<UserDto> result = testRepositoryService.findAll();

        //then
        verify(userRepository).findAll();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(account.getId());
        assertThat(result.get(1).getId()).isEqualTo(account2.getId());
    }
}

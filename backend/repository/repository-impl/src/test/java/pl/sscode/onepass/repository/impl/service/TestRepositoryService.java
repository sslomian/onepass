package pl.sscode.onepass.repository.impl.service;

import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserRepository;
import pl.sscode.onepass.repository.api.repository.impl.service.AbstractRepositoryService;

/**
 * Created by sscode on 2017-06-15.
 */
public class TestRepositoryService extends AbstractRepositoryService<UserDto, User, Long, UserRepository> {

    public TestRepositoryService(UserRepository repository) {
        super(repository);
    }

    @Override
    protected User convertToEntity(UserDto dto) {
        return new User();
    }

    @Override
    protected UserDto convertToDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        return userDto;
    }

    @Override
    public void delete(Long id) {
    }
}

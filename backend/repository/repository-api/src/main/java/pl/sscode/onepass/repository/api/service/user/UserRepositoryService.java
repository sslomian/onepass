package pl.sscode.onepass.repository.api.service.user;

import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.service.RepositoryService;

/**
 * Created by sscode on 2017-06-15.
 */
public interface UserRepositoryService extends RepositoryService<UserDto, Long> {

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

}

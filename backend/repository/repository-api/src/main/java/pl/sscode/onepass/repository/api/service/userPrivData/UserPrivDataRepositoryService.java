package pl.sscode.onepass.repository.api.service.userPrivData;

import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.dto.UserPrivDataDto;
import pl.sscode.onepass.repository.api.service.RepositoryService;

import java.util.List;

/**
 * Created by sscode on 2017-07-21.
 */
public interface UserPrivDataRepositoryService extends RepositoryService<UserPrivDataDto, Long> {

    UserPrivDataDto findBySite(String site);
    List<UserPrivDataDto> findByUserDto(UserDto userDto);

}

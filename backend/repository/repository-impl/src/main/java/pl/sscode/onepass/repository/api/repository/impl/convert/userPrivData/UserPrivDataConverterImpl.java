package pl.sscode.onepass.repository.api.repository.impl.convert.userPrivData;

import org.springframework.stereotype.Service;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.dto.UserPrivDataDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.entities.UserPrivData;
import pl.sscode.onepass.repository.api.repository.api.converter.Converter;

/**
 * Created by sscode on 2017-07-21.
 */
@Service
public class UserPrivDataConverterImpl implements Converter<UserPrivData, UserPrivDataDto> {

    private final Converter<User, UserDto> userConverter;

    public UserPrivDataConverterImpl(Converter<User, UserDto> userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public UserPrivDataDto convertTo(UserPrivData src) {
        if (src == null) {
            return null;
        }

        UserPrivDataDto target = new UserPrivDataDto();

        target.setId(src.getId());
        target.setUsernameOrEmail(src.getUsernameOrEmail());
        target.setPassword(src.getPassword());
        target.setSite(src.getSite());
        target.setDescription(src.getDescription());

        UserDto userDto = userConverter.convertTo(src.getUser());

        target.setUser(userDto);

        return target;
    }

    @Override
    public UserPrivData convertFrom(UserPrivDataDto src) {
        if (src == null) {
            return null;
        }

        UserPrivData target = new UserPrivData();

        target.setId(src.getId());
        target.setUsernameOrEmail(src.getUsernameOrEmail());
        target.setPassword(src.getPassword());
        target.setSite(src.getSite());
        target.setDescription(src.getDescription());

        User user = userConverter.convertFrom(src.getUser());

        target.setUser(user);

        return target;
    }
}

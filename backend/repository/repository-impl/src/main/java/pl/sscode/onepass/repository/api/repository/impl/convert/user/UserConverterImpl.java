package pl.sscode.onepass.repository.api.repository.impl.convert.user;

import org.springframework.stereotype.Service;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.entities.Authority;
import pl.sscode.onepass.repository.api.entities.AuthorityType;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.repository.api.converter.Converter;

import java.util.List;

/**
 * Created by sscode on 2017-06-15.
 */
@Service
public class UserConverterImpl implements Converter<User, UserDto> {

    @Override
    public UserDto convertTo(User src) {
        if (src == null) {
            return null;
        }

        UserDto target = new UserDto();

        target.setId(src.getId());
        target.setUsername(src.getUsername());
        target.setPassword(src.getPassword());
        target.setEmail(src.getEmail());
        target.setPrivateKey(src.getPrivateKey());
/*        target.setCreated(src.getCreated());
        target.setUpdated(src.getUpdated());
        target.setLastLoginTime(src.getLastLoginTime());*/

        /*List<Authority> authorities = src.getAuthorities();
        if (authorities != null) {
            authorities.forEach(item -> target.getAuthorities().add(item.getAuthority()));
        }*/

        return target;
    }

    @Override
    public User convertFrom(UserDto src) {
        if (src == null) {
            return null;
        }

        User target = new User();

        target.setId(src.getId());
        target.setUsername(src.getUsername());
        target.setPassword(src.getPassword());
        target.setEmail(src.getEmail());
        target.setPrivateKey(src.getPrivateKey());
/*        target.setCreated(src.getCreated());
        target.setUpdated(src.getUpdated());
        target.setLastLoginTime(src.getLastLoginTime());*/
        return target;
    }
}

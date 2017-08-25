package pl.sscode.onepass.rest.impl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sscode on 2017-08-01.
 */

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public CustomUserDetailsService(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto userDto = userRepositoryService.findByUsername(username);
            if (userDto == null) {
                return null;
            }
            return new User(userDto.getUsername(), userDto.getPassword(), getAuthorities(userDto));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(UserDto userDto) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userDto.getAuthorities().forEach(item -> authorities.add(new SimpleGrantedAuthority(item.toString())));
        /*authorities.add(new SimpleGrantedAuthority("USER"));*/
        return authorities;
    }
}

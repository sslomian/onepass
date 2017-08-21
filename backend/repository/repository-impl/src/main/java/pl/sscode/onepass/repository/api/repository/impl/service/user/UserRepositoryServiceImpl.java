package pl.sscode.onepass.repository.api.repository.impl.service.user;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.repository.api.converter.Converter;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserRepository;
import pl.sscode.onepass.repository.api.repository.impl.service.AbstractRepositoryService;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;

import javax.transaction.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by sscode on 2017-06-15.
 */
@Transactional
@Service
public class UserRepositoryServiceImpl extends AbstractRepositoryService<UserDto, User, Long, UserRepository> implements UserRepositoryService {

    private final Logger logger = getLogger(UserRepositoryServiceImpl.class);

    private final Converter<User, UserDto> userConverter;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryServiceImpl(UserRepository repository, Converter<User, UserDto> userConverter, @Lazy PasswordEncoder passwordEncoder) {
        super(repository);
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
        logger.info("User with id: {} is deleted.", id);
    }

    @Override
    public UserDto findByUsername(String username) {
            User entity = repository.findByUsername(username);
            logger.info("Found user for name: {} -> {}", username, entity);
            return convertToDto(entity);
    }

    @Override
    public UserDto findByEmail(String email) {
        User entity = repository.findByEmail(email);
        logger.info("Found user for email: {} -> {}", email, entity);
        return convertToDto(entity);
    }

    @Override
    public UserDto save(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        logger.info("Saving object: {}", dto);

        User entity = convertToEntity(dto);
        User saved = repository.save(entity);

        return convertToDto(saved);
    }

    private UserDto encodePassword(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userDto;
    }

    @Override
    protected User convertToEntity(UserDto dto) {
        return userConverter.convertFrom(dto);
    }

    @Override
    protected UserDto convertToDto(User entity) {
        return userConverter.convertTo(entity);
    }
}


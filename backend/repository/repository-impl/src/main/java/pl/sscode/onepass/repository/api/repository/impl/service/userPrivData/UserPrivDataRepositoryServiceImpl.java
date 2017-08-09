package pl.sscode.onepass.repository.api.repository.impl.service.userPrivData;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.dto.UserPrivDataDto;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.entities.UserPrivData;
import pl.sscode.onepass.repository.api.repository.api.converter.Converter;
import pl.sscode.onepass.repository.api.repository.impl.repository.UserPrivDataRepository;
import pl.sscode.onepass.repository.api.repository.impl.service.AbstractRepositoryService;
import pl.sscode.onepass.repository.api.service.userPrivData.UserPrivDataRepositoryService;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by sscode on 2017-07-21.
 */
@Transactional
@Service
public class UserPrivDataRepositoryServiceImpl extends AbstractRepositoryService<UserPrivDataDto, UserPrivData, Long, UserPrivDataRepository> implements UserPrivDataRepositoryService {

    private final Logger logger = getLogger(UserPrivDataRepositoryServiceImpl.class);

    private final Converter<UserPrivData, UserPrivDataDto> userPrivDataConverter;

    private final Converter<User, UserDto> userConverter;

    @Autowired
    public UserPrivDataRepositoryServiceImpl(UserPrivDataRepository repository, Converter<UserPrivData, UserPrivDataDto> userPrivDataConverter, Converter<User, UserDto> userConverter) {
        super(repository);
        this.userPrivDataConverter = userPrivDataConverter;
        this.userConverter = userConverter;
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
        logger.info("UserPrivData with id: {} is deleted.", id);
    }

    @Override
    public UserPrivDataDto findBySite(String site) {
        UserPrivData entity = repository.findBySite(site);
        logger.info("Found userPrivData for site: {} -> {}", site, entity);
        return convertToDto(entity);
    }

    @Override
    public List<UserPrivDataDto> findByUserDto(UserDto userDto) {
        User user = userConverter.convertFrom(userDto);
        List<UserPrivDataDto> userPrivDataDtoList = new ArrayList<>();
        repository.findByUser(user).forEach(userPrivData -> {
            userPrivDataDtoList.add(convertToDto(userPrivData));
        });
        return userPrivDataDtoList;
    }

    @Override
    public UserPrivDataDto save(UserPrivDataDto dto) {
        logger.info("Saving object: {}", dto);
        UserPrivData entity = convertToEntity(dto);
        UserPrivData saved = repository.save(entity);

        return convertToDto(saved);
    }

    @Override
    protected UserPrivData convertToEntity(UserPrivDataDto dto) {
        return userPrivDataConverter.convertFrom(dto);
    }

    @Override
    protected UserPrivDataDto convertToDto(UserPrivData entity) {
        return userPrivDataConverter.convertTo(entity);
    }
}

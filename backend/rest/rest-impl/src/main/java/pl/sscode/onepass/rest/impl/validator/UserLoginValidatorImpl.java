package pl.sscode.onepass.rest.impl.validator;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;
import pl.sscode.onepass.rest.api.model.ResponseDto;
import pl.sscode.onepass.rest.api.security.SecurityService;
import pl.sscode.onepass.rest.api.validation.Validator;
import pl.sscode.onepass.rest.api.validation.Error;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by sscode on 2017-08-08.
 */
@Component
public class UserLoginValidatorImpl implements Validator<UserDto> {

    private final Logger logger = getLogger(UserLoginValidatorImpl.class);

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private SecurityService securityService;

    @Override
    public ResponseDto<UserDto> validate(Map<Class, Object> params) {
        UserDto userDto = (UserDto) params.get(UserDto.class);
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        findUser(userDto.getUsername(), responseDto);
        if(!ArrayUtils.isEmpty(responseDto.getErrors().toArray())) {
            return responseDto;
        }
        loginUser(userDto.getUsername(), userDto.getPassword(), responseDto);
        return responseDto;
    }

    private void findUser(String username, ResponseDto<UserDto> responseDto) {
        if (userRepositoryService.findByUsername(username) != null) {
            return;
        }

        responseDto.getErrors().add(new Error(ControllerErrorCodes.USER_NOT_FOUND.getCode(), ControllerErrorCodes.USER_NOT_FOUND.getMsg()));
    }

    private void loginUser(String username, String password, ResponseDto<UserDto> responseDto) {
        try {
            securityService.login(username, password);
        } catch (BadCredentialsException e) {
            responseDto.getErrors().add(new Error(ControllerErrorCodes.WRONG_PASSWORD.getCode(), ControllerErrorCodes.WRONG_PASSWORD.getMsg()));
        }
    }
}

package pl.sscode.onepass.rest.impl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;
import pl.sscode.onepass.rest.api.model.ResponseDto;
import pl.sscode.onepass.rest.api.validation.Validator;
import pl.sscode.onepass.rest.api.validation.Error;
import pl.sscode.onepass.rest.impl.validator.ControllerErrorCodes.*;

import java.util.Map;

/**
 * Created by sscode on 2017-08-08.
 */
@Component
public class UserLoginValidatorImpl implements Validator<UserDto> {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Override
    public ResponseDto<UserDto> validate(Map<Class, Object> params) {
        UserDto userDto = (UserDto) params.get(UserDto.class);
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        findUser(userDto.getUsername(), responseDto);
        return responseDto;
    }

    private void findUser(String username, ResponseDto<UserDto> responseDto) {
        if(userRepositoryService.findByUsername(username) != null) {
            return;
        }

        responseDto.getErrors().add(new Error(ControllerErrorCodes.USER_NOT_FOUND.getCode(), ControllerErrorCodes.USER_NOT_FOUND.getMsg()));
    }
}

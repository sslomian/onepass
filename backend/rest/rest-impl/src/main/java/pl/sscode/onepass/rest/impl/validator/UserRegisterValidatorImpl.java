package pl.sscode.onepass.rest.impl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;
import pl.sscode.onepass.rest.api.model.ResponseDto;
import pl.sscode.onepass.rest.api.validation.Error;
import pl.sscode.onepass.rest.api.validation.Validator;

import java.util.Map;

/**
 * Created by sscode on 2017-08-11.
 */
@Component
public class UserRegisterValidatorImpl implements Validator<UserDto> {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Override
    public ResponseDto<UserDto> validate(Map<Class, Object> params) {
        UserDto userDto = (UserDto) params.get(UserDto.class);
        ResponseDto<UserDto> responseDto = new ResponseDto<>();

        checkUsername(userDto.getUsername(), responseDto);

        checkEmail(userDto.getEmail(), responseDto);

        return responseDto;
    }

    private void checkUsername(String username, ResponseDto<UserDto> responseDto) {
        if (userRepositoryService.findByUsername(username) == null) {
            return;
        }
        responseDto.getErrors().add(new Error(ControllerErrorCodes.USERNAME_ALREADY_USE.getCode(), ControllerErrorCodes.USERNAME_ALREADY_USE.getMsg()));
    }

    private void checkEmail(String email, ResponseDto<UserDto> responseDto) {
        if (userRepositoryService.findByEmail(email) == null) {
            return;
        }
        responseDto.getErrors().add(new Error(ControllerErrorCodes.EMAIL_ALREADY_USE.getCode(), ControllerErrorCodes.EMAIL_ALREADY_USE.getMsg()));

    }
}

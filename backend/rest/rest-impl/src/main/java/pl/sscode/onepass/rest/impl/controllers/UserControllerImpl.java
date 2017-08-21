package pl.sscode.onepass.rest.impl.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;
import pl.sscode.onepass.rest.api.controllers.UserController;
import pl.sscode.onepass.rest.api.model.ResponseDto;
import pl.sscode.onepass.rest.api.security.SecurityService;
import pl.sscode.onepass.rest.api.validation.Validate;
import pl.sscode.onepass.rest.impl.validator.UserLoginValidatorImpl;
import pl.sscode.onepass.rest.impl.validator.UserRegisterValidatorImpl;

import java.security.Principal;
import java.util.List;

/**
 * Created by sscode on 2017-06-18.
 */
@RestController
public class UserControllerImpl implements UserController {

    private final UserRepositoryService userRepositoryService;
    private final SecurityService securityService;

    public UserControllerImpl(UserRepositoryService userRepositoryService, SecurityService securityService) {
        this.userRepositoryService = userRepositoryService;
        this.securityService = securityService;
    }

    public ResponseDto<UserDto> findByUserName(@PathVariable("username") String username) {
        return new ResponseDto(userRepositoryService.findByUsername(username));
    }

    public void delete(@PathVariable("id") Long id) {
        userRepositoryService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDto<List<UserDto>> findAll() {
        return new ResponseDto(userRepositoryService.findAll());
    }


    @Validate(value = {UserRegisterValidatorImpl.class})
    public ResponseDto<UserDto> save(@RequestBody UserDto userDto) {
        UserDto user = userRepositoryService.save(userDto);
        return new ResponseDto(user);
    }

    public ResponseDto<UserDto> update(@RequestBody UserDto userDto) {
        UserDto user = userRepositoryService.save(userDto);
        return new ResponseDto(user);
    }

    @Validate(value = {UserLoginValidatorImpl.class})
    public ResponseDto<UserDto> login(@RequestBody UserDto userDto) {
        return new ResponseDto();
    }

    public UserDto loggedUser(Principal principal) {
        if (principal != null) {
            User activeUser = (User) ((Authentication) principal).getPrincipal();
            UserDto userDto = userRepositoryService.findByUsername(activeUser.getUsername());
            UserDto userDtoSend = new UserDto();
            userDtoSend.setId(userDto.getId());
            userDtoSend.setUsername(userDto.getUsername());
            userDtoSend.setEmail(userDto.getEmail());
            userDtoSend.setPrivateKey(userDto.getPrivateKey());

            return userDtoSend;
        } else
            return new UserDto();
    }

    public String isLogged(Principal principal) {
        if (principal != null) {
            return "authorised";
        }
        return "unauthorised";
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

}

package pl.sscode.onepass.rest.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.repository.api.dto.UserPrivDataDto;
import pl.sscode.onepass.repository.api.service.user.UserRepositoryService;
import pl.sscode.onepass.repository.api.service.userPrivData.UserPrivDataRepositoryService;
import pl.sscode.onepass.rest.api.controllers.UserPrivDataController;
import pl.sscode.onepass.rest.api.model.ResponseDto;

import java.security.Principal;
import java.util.List;

/**
 * Created by sscode on 2017-07-23.
 */
@RestController
public class UserPrivDataControllerImpl implements UserPrivDataController {

    private final UserPrivDataRepositoryService userPrivDataRepositoryService;

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public UserPrivDataControllerImpl(UserPrivDataRepositoryService userPrivDataRepositoryService, UserRepositoryService userRepositoryService) {
        this.userPrivDataRepositoryService = userPrivDataRepositoryService;
        this.userRepositoryService = userRepositoryService;
    }

    @RequestMapping(value = "api/userPrivData/save", method = RequestMethod.POST)
    public ResponseDto<UserPrivDataDto> save(@RequestBody UserPrivDataDto userPrivDataDto, Principal principal) {
        User activeUser = (User) ((Authentication) principal).getPrincipal();
        UserDto userDto = userRepositoryService.findByUsername(activeUser.getUsername());
        userPrivDataDto.setUser(userDto);
        UserPrivDataDto userPrivData = userPrivDataRepositoryService.save(userPrivDataDto);

        return new ResponseDto(userPrivData);
    }

    @RequestMapping(value = "api/userPrivData", method = RequestMethod.GET)
    public ResponseDto<List<UserPrivDataDto>> findAll(Principal principal) {
        User activeUser = (User) ((Authentication) principal).getPrincipal();
        UserDto userDto = userRepositoryService.findByUsername(activeUser.getUsername());

        return new ResponseDto(userPrivDataRepositoryService.findByUserDto(userDto));
    }

    @RequestMapping(value = "api/userPrivData/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id) {
        userPrivDataRepositoryService.delete(id);
    }

}

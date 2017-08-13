package pl.sscode.onepass.rest.api.controllers;

import org.springframework.web.bind.annotation.*;
import pl.sscode.onepass.repository.api.dto.UserDto;
import pl.sscode.onepass.rest.api.model.ResponseDto;

import java.security.Principal;
import java.util.List;

/**
 * Created by sscode on 2017-06-18.
 */
public interface UserController extends RestController<ResponseDto<UserDto>> {

    @RequestMapping(value = "/api/findByUserName/{username}", method = RequestMethod.GET)
    @ResponseBody
    ResponseDto<UserDto> findByUserName(@PathVariable("username") String username);

    @RequestMapping("/delete/{id}")
    @ResponseBody
    void delete(@PathVariable("id") Long id);

    @RequestMapping(value = "api/users")
    @ResponseBody
    ResponseDto<List<UserDto>> findAll();

    @RequestMapping(value = "api/users/save", method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<UserDto> save(@RequestBody UserDto userDto);

    @RequestMapping(value = "api/users/update", method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<UserDto> update(@RequestBody UserDto userDto);

    @RequestMapping(value = "/register}", method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<UserDto> register(@RequestBody UserDto userDto);

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<UserDto> login(@RequestBody UserDto userDto);

    @RequestMapping(value = "api/loggedUser", method = RequestMethod.GET)
    UserDto loggedUser(Principal principal);

    @RequestMapping(value = "api/isLogged", method = RequestMethod.GET)
    String isLogged(Principal principal);

    @RequestMapping(value = "api/logout", method = RequestMethod.GET)
    void logout();
}

package pl.sscode.onepass.rest.api.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sscode.onepass.repository.api.dto.UserPrivDataDto;
import pl.sscode.onepass.rest.api.model.ResponseDto;

import java.security.Principal;
import java.util.List;

/**
 * Created by sscode on 2017-07-23.
 */
public interface UserPrivDataController extends RestController<ResponseDto<UserPrivDataDto>> {

    @ResponseBody
    ResponseDto<UserPrivDataDto> save(@RequestBody UserPrivDataDto userPrivDataDto, Principal principal);

    @ResponseBody
    ResponseDto<List<UserPrivDataDto>> findAll(Principal principal);

    @ResponseBody
    void delete(@PathVariable("id") Long id);
}

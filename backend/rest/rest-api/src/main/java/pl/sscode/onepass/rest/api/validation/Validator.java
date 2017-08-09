package pl.sscode.onepass.rest.api.validation;

import pl.sscode.onepass.rest.api.model.ResponseDto;

import java.util.Map;

/**
 * Created by sscode on 2017-08-06.
 */
public interface Validator<T> {
    ResponseDto<T> validate(Map<Class, Object> params);
}

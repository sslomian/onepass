package pl.sscode.onepass.rest.impl.aspect;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.sscode.onepass.rest.api.model.ResponseDto;
import pl.sscode.onepass.rest.api.validation.Validate;
import pl.sscode.onepass.rest.api.validation.Validator;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by sscode on 2017-08-06.
 */

@Component
@Aspect
public class ValidationAspect {

    private final Logger logger = getLogger(ValidationAspect.class);

    private final ApplicationContext applicationContext;

    @Autowired
    public ValidationAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Around(value = "@annotation(annotation)")
    public Object validate(ProceedingJoinPoint joinPoint, Validate annotation) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            throw new IllegalStateException(format("Method %s with no parameters can't be validated", methodSignature));
        }

        Class[] parameterTypes = methodSignature.getParameterTypes();
        Map<Class, Object> paramsMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            paramsMap.put(parameterTypes[i], args[i]);
        }

        Class<? extends Validator>[] validatorClasses = annotation.value();

        ResponseDto responseDto = null;
        ResponseDto responseDtoFinal = new ResponseDto();
        for (Class<? extends Validator> validatorClass : validatorClasses) {
            Validator validator = applicationContext.getBean(validatorClass);
            responseDto = validator.validate(paramsMap);
            if (responseDto != null) {
                if (responseDto.getErrors() != null) {
                    responseDtoFinal.getErrors().addAll(responseDto.getErrors());
                }
                if (responseDtoFinal.getObject() == null) {
                    responseDtoFinal.setObject(responseDto.getObject());
                }
            }
        }

        if (CollectionUtils.isEmpty(responseDtoFinal.getErrors())) {
            return joinPoint.proceed();
        }

        return responseDtoFinal;
    }
}

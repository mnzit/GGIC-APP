package com.ggic.app.util;

import com.ggic.app.builder.ResponseBuilder;
import com.ggic.app.response.Response;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static Optional<Response> validate(Object object) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Map<String, String> errors = validator
                .validate(object)
                .stream()
                .collect(Collectors.toMap((cv) -> cv.getPropertyPath().toString(), (cv) -> cv.getMessage()));
        return Optional.ofNullable(!errors.isEmpty() ? ResponseBuilder.failure("Invalid Request", errors) : null);
    }
}

package com.naosim.dddwork.kintai.shared.easy;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.kintai.shared.exception.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public interface Validatable {

    String DEFAULT_ERROR_MESSAGE = "バリデーションエラー発生．";


    default <T> void validate(T target) throws ValidationException {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> result = validator.validate(target);
        if (result.isEmpty()) {
            return;
        }

        ImmutableList<String> errorMessages = result.stream()
                .map(ConstraintViolation::getMessage)
                .collect(ImmutableList.toImmutableList());
        throw new ValidationException(DEFAULT_ERROR_MESSAGE, errorMessages);
    }
}

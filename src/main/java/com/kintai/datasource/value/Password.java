package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

public class Password {
    @Getter
    protected String password;

    public Password(String password) throws ValidatorException {
        validate(password);
        this.password = password;
    }

    protected void validate(String password) throws ValidatorException {
        isRequired(password);
    }

    protected void isRequired(String password) throws ValidatorException {
        if(!StringUtils.hasText(password)) {
            throw new ValidatorException("パスワードは必須です。");
        }
    }
}

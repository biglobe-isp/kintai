package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * パスワードを管理するValue Object
 */
public class Password {
    // パスワードの値
    @Getter
    protected String password;

    /**
     * コンストラクタ
     * 引数のパスワードのバリデーションチェックを実施し、異常を検知した場合は{@link ValidatorException}をスローします。
     * @param password パスワード
     * @throws ValidatorException 異常を検知した場合にスロー
     */
    public Password(String password) throws ValidatorException {
        validate(password);
        this.password = password;
    }

    /**
     * パスワードのバリデーションチェックを実施します。チェックで異常を検知した場合は{@link ValidatorException}をスローします。
     * @param password パスワード
     * @throws ValidatorException バリデーションチェックで異常を検知した場合にスロー
     */
    protected void validate(String password) throws ValidatorException {
        isRequired(password);
    }

    /**
     * パスワードの必須チェックをします。
     * @param password パスワード
     * @throws ValidatorException パスワードがnullや空文字。スペースのみ(半角、全角両方)の場合、{@link ValidatorException}をスローします。
     */
    protected void isRequired(String password) throws ValidatorException {
        if(!StringUtils.hasText(password)) {
            throw new ValidatorException("パスワードは必須です。");
        }
    }
}

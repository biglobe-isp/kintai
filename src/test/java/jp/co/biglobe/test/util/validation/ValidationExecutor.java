package jp.co.biglobe.test.util.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Validation実行用クラス
 */
public class ValidationExecutor {
    private ValidationExecutor(){}

    /**
     * Validatorの生成
     */
    private static Validator validator;
    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    /**
     * バリデーションの実行
     */
    public static <T> Set<ConstraintViolation<T>> validate(T object){
        return validator.validate(object);
    }
}

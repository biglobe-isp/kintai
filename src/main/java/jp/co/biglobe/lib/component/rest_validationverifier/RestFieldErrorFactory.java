package jp.co.biglobe.lib.component.rest_validationverifier;

import org.springframework.validation.FieldError;

@SuppressWarnings("ALL")
public class RestFieldErrorFactory {

    public static FieldError create(
            String field,
            Object value,
            String reason,
            String message
    ) {

        String[] reasons = {reason};

        return new FieldError(
                "", /* オブジェクト名は返却しないため空文字(NotNull項目のためnullにしてない) */
                field,
                value,
                false,
                reasons,
                null, /* 返却しないためnull */
                message
        );
    }
}

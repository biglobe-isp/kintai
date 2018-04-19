package jp.co.biglobe.lib.danger.validationverifier;

/**
 * バリデーションエラーの詳細オブジェクト
 */
@SuppressWarnings("ALL")
public class RestValidationErrorDetail {
    public final String validatedField;
    public final String rejectedReason;
    public final String rejectedValue;
    public final String message;

    RestValidationErrorDetail(
            final String validatedField,
            final String rejectedReason,
            final String rejectedValue,
            final String message
    ) {
        this.validatedField = validatedField;
        this.rejectedReason = rejectedReason;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public static RestValidationErrorDetailBuilder builder() {
        return new RestValidationErrorDetailBuilder();
    }

    public String toString() {
        return String.format("[ validatedField:%s, rejectedReason:%s, rejectedValue:%s, message:%s]",
                validatedField, rejectedReason, rejectedValue, message);
    }
}

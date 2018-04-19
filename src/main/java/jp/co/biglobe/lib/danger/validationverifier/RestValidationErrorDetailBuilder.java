package jp.co.biglobe.lib.danger.validationverifier;

/**
 * バリデーションエラーの詳細オブジェクトのビルダー
 */
public class RestValidationErrorDetailBuilder {
    private String validatedField;
    private String rejectedReason;
    private String rejectedValue;
    private String message;

    RestValidationErrorDetailBuilder() {
    }

    public RestValidationErrorDetailBuilder setValidatedField(final String validatedField) {
        this.validatedField = validatedField;
        return this;
    }

    public RestValidationErrorDetailBuilder setRejectedReason(final String rejectedReason) {
        this.rejectedReason = rejectedReason;
        return this;
    }

    public RestValidationErrorDetailBuilder setRejectedValue(final Object rejectedValue) {
        this.rejectedValue = (rejectedValue == null ? null : rejectedValue.toString());
        return this;
    }

    public RestValidationErrorDetailBuilder setMessage(final String message) {
        this.message = message;
        return this;
    }

    public RestValidationErrorDetail build() {
        return new RestValidationErrorDetail(validatedField, rejectedReason, rejectedValue, message);
    }

}

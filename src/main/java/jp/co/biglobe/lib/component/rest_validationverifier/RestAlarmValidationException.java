package jp.co.biglobe.lib.component.rest_validationverifier;

import jp.co.biglobe.lib.publication.exception.errorlist.HasRestErrorList;
import jp.co.biglobe.lib.publication.exception.type.AlarmException;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * バリデーションエラー時に発生する例外（アラームメール飛ばす）
 */
@SuppressWarnings("ALL")
final class RestAlarmValidationException extends AlarmException implements HasRestErrorList {

    private List<?> errorList;

    private HttpStatus httpStatus;

    public RestAlarmValidationException(String message, List<?> errorList, HttpStatus httpStatus) {
        super(message);

        this.errorList = errorList;
        this.httpStatus = httpStatus;

    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public List<?> getErrorList() {
        return errorList;
    }


}

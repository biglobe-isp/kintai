package jp.co.biglobe.lib.component.rest_validationverifier;

import jp.co.biglobe.lib.component.validationverifier.AlarmValidationVerifierImpl;
import jp.co.biglobe.lib.danger.validationverifier.RestValidationErrorDetail;
import jp.co.biglobe.lib.plugin.log.LoggerWrapper;
import jp.co.biglobe.lib.plugin.rest_validationverifier.RestAlarmValidationVerifier;
import jp.co.biglobe.lib.publication.exception.type.AlarmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;


/**
 * バリデーション結果をチェックし、エラーが含まれていれば例外をスロー（アラームメールを飛ばす）
 */
@Component
public class RestAlarmValidationVerifierImpl implements RestAlarmValidationVerifier {

    @Autowired
    private RestErrorListCreator errorListCreator;

    @Autowired
    private LoggerWrapper loggerWrapper;

    /**
     * バリデーション結果をチェックし、エラーが含まれていれば例外をスロー（アラームメールを飛ばす）
     *
     * @param errors バリデーション結果
     * @throws AlarmException バリデーションエラーが発生していたらスローする
     */
    @Override
    public void verify(Errors errors) throws AlarmException {
        if (errors.hasErrors()) {
            List<RestValidationErrorDetail> errorDetails = errorListCreator.create(errors.getFieldErrors());
            logErrors(errorDetails);
            throw new RestAlarmValidationException("バリデーションエラーが発生しました", errorDetails, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * バリデーションエラーのエラーログを出力する
     *
     * @param errorList バリデーションエラーリスト
     */
    private void logErrors(List<RestValidationErrorDetail> errorList) {
        logError("Caused by: " + errorList.stream().map(RestValidationErrorDetail::toString).collect(Collectors.joining(",")));
    }

    /**
     * ERRORレベルのログを出力する
     *
     * @param message ログメッセージ
     */
    private void logError(final String message) {
        final String methodPath = AlarmValidationVerifierImpl.class + "#logError()";
        loggerWrapper.error(methodPath, message);
    }


}

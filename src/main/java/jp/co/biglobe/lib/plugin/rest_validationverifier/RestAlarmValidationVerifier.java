package jp.co.biglobe.lib.plugin.rest_validationverifier;

import jp.co.biglobe.lib.publication.exception.type.AlarmException;
import org.springframework.validation.Errors;

/**
 * バリデーション結果をチェック（アラームメールを飛ばす）
 */
@SuppressWarnings("ALL")
public interface RestAlarmValidationVerifier {

    /**
     * バリデーション結果をチェックし、エラーが含まれていれば例外をスロー（アラームメールを飛ばす）
     *
     * @param errors バリデーション結果
     * @throws AlarmException バリデーションエラーが発生していたらスローする
     */
    public void verify(Errors errors) throws AlarmException;

}

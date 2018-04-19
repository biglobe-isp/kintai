package jp.co.biglobe.lib.publication.exception;

import jp.co.biglobe.lib.publication.exception.errorstatus.ErrorType;
import jp.co.biglobe.lib.publication.exception.errorstatus.HasHttpErrorStatus;
import jp.co.biglobe.lib.publication.exception.type.NoTransferException;
import jp.co.biglobe.lib.publication.valueobject.ErrorKeyWordValue;
import jp.co.biglobe.lib.publication.valueobject.ErrorStatusAndMessage;
import org.springframework.http.HttpStatus;

/**
 * ビジネス例外
 * <p>
 * エラーメールによる通知は行わない
 */
public class SimpleBusinessException extends NoTransferException implements HasHttpErrorStatus {

    /**
     * エラーメッセージ
     */
    private final String errorMessage;

    /**
     * httpエラーステータス
     */
    private final HttpStatus httpStatus;

    /**
     * エラー種別
     */
    private ErrorType errorType;

    /**
     * コンストラクタ (キーの出力なし)
     *
     * @param errorStatusAndMessage エラーステータス
     */
    public SimpleBusinessException(final ErrorStatusAndMessage errorStatusAndMessage) {
        super(errorStatusAndMessage.getErrorMessage());
        this.errorMessage = errorStatusAndMessage.getErrorMessage();
        this.httpStatus = errorStatusAndMessage.getHttpStatus();
    }

    /**
     * コンストラクタ (キーの出力あり)
     *
     * @param errorStatusAndMessage エラーステータス
     * @param errorKeyWordValue     エラーメッセージに出力するキー情報
     */
    public SimpleBusinessException(final ErrorStatusAndMessage errorStatusAndMessage, final ErrorKeyWordValue errorKeyWordValue) {
        super(errorStatusAndMessage.getErrorMessage(errorKeyWordValue));
        this.errorMessage = errorStatusAndMessage.getErrorMessage(errorKeyWordValue);
        this.httpStatus = errorStatusAndMessage.getHttpStatus();
    }

    /**
     * エラー種別をセットする
     *
     * @param errorType エラー種別
     */
    public SimpleBusinessException setErrorType(ErrorType errorType) {
        this.errorType = errorType;
        return this;
    }

    /**
     * ビジネスエラーステータスを取得
     *
     * @return エラーステータス
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }
}

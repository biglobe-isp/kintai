package jp.co.biglobe.lib.essential.rest_exception_handler;


import jp.co.biglobe.lib.plugin.exceptionhandler.ExceptionLogHandler;
import jp.co.biglobe.lib.plugin.exceptionhandler.ExceptionTransferHandler;
import jp.co.biglobe.lib.plugin.rest_exceptionhandler.RestExceptionHandler;
import jp.co.biglobe.lib.plugin.rest_exceptionhandler.RestExceptionResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * グローバルなExceptionハンドリングを行い、クライアントにはエラーレスポンスを返す
 * <p>
 * 基本的に全ての例外はRuntimeExceptionとしてスローし、ここでハンドリングを行う
 */
@ControllerAdvice
public final class RestExceptionHandlerImpl implements RestExceptionHandler {

    /**
     * Handler関連
     */

    @Autowired
    private ExceptionTransferHandler exceptionTransferHandler;

    @Autowired
    private ExceptionLogHandler exceptionLogHandler;

    @Autowired
    private RestExceptionResponseHandler restExceptionResponseHandler;

    /**
     * Exceptionをハンドリング
     *
     * @param e 例外
     * @return エラーレスポンス
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<Map> handleAndResponse(Throwable e) {

        // ロギング
        exceptionLogHandler.handle(e);

        // エラーメール通知
        exceptionTransferHandler.handle(e);

        // エラーレスポンスの返却
        return restExceptionResponseHandler.handle(e);
    }

}
package jp.co.biglobe.lib.plugin.rest_exceptionhandler;


import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * グローバルなExceptionハンドリングを行い、クライアントにはエラーレスポンスを返す
 * <p>
 * 基本的に全ての例外はRuntimeExceptionとしてスローし、ここでハンドリングを行う
 */
public interface RestExceptionHandler {
    /**
     * Exceptionをハンドリング
     *
     * @param e 例外
     * @return エラーレスポンス
     */
    ResponseEntity<Map> handleAndResponse(Throwable e);
}

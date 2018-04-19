package jp.co.biglobe.lib.plugin.rest_responce.error;


import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * 例外発生時のデフォルトレスポンス
 */
public interface RestErrorResponse {

    /**
     * 例外発生時のJSONレスポンスの生成
     *
     * @param e スローされた例外
     * @return JsonレスポンスのMap
     */
    ResponseEntity<Map> build(Throwable e);
}

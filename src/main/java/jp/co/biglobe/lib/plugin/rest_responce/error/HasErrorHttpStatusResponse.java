package jp.co.biglobe.lib.plugin.rest_responce.error;


import jp.co.biglobe.lib.publication.exception.errorstatus.HasHttpErrorStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * HTTPステータスエラーを保持した例外発生時のデフォルトレスポンス
 */
public interface HasErrorHttpStatusResponse {

    /**
     * HTTPエラーステータスを保持した例外発生時のJSONレスポンスの生成
     *
     * @param e スローされた例外
     * @return JsonレスポンスのMap
     */
    ResponseEntity<Map> build(HasHttpErrorStatus e);
}

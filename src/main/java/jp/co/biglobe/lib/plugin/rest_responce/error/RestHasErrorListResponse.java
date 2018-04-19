package jp.co.biglobe.lib.plugin.rest_responce.error;


import jp.co.biglobe.lib.publication.exception.errorlist.HasRestErrorList;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * エラーリストを保持した例外発生時のデフォルトレスポンス
 */
public interface RestHasErrorListResponse {

    /**
     * エラーリストを保持した例外発生時のJSONレスポンスの生成
     *
     * @param e スローされた例外
     * @return JsonレスポンスのMap
     */
    ResponseEntity<Map> build(HasRestErrorList e);
}

package jp.co.biglobe.lib.component.rest_response.error

import jp.co.biglobe.lib.http.ResponseEntityFactory
import jp.co.biglobe.lib.plugin.rest_responce.error.RestErrorResponse
import jp.co.biglobe.lib.plugin.rest_view.RestJsonSystemErrorTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * 例外発生時のデフォルトレスポンス
 */
@SuppressWarnings("ALL")
@Component
public final class RestErrorResponseImpl implements RestErrorResponse {

    @Autowired
    private RestJsonSystemErrorTemplate restJsonSystemErrorTemplate

    @Autowired
    private ResponseEntityFactory responseEntityFactory

    /**
     * 例外発生時のJSONレスポンスの生成
     *
     * @param e スローされた例外
     * @param statusCode ステータスコード
     * @return JsonレスポンスのMap
     */
    @Override
    public ResponseEntity<Map> build(Throwable e) {

        Map jsonResult = restJsonSystemErrorTemplate.build([:])

        return responseEntityFactory.createBuilder().buildInternalServerError(jsonResult)

    }

}

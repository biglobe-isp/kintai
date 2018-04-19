package jp.co.biglobe.lib.component.rest_response.error

import jp.co.biglobe.lib.http.ResponseEntityFactory
import jp.co.biglobe.lib.plugin.rest_responce.error.HasErrorHttpStatusResponse
import jp.co.biglobe.lib.plugin.rest_view.RestJsonErrorTemplate
import jp.co.biglobe.lib.publication.exception.errorstatus.HasHttpErrorStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * エラーステータスを保持した例外発生時のデフォルトレスポンス
 */
@SuppressWarnings("ALL")
@Component
public final class HasErrorHttpStatusResponseImpl implements HasErrorHttpStatusResponse {

    @Autowired
    private RestJsonErrorTemplate restJsonErrorTemplate

    @Autowired
    private ResponseEntityFactory responseEntityFactory

    /**
     * エラーステータスを保持した例外発生時のJSONレスポンスの生成
     *
     * @param e スローされた例外
     * @return JsonレスポンスのMap
     */
    @Override
    public ResponseEntity<Map> build(HasHttpErrorStatus e) {

        Map body = restJsonErrorTemplate.build([:], e)

        return responseEntityFactory.createBuilder().buildByStatusAndBody(e.httpStatus, body)
    }

}

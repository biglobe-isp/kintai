package jp.co.biglobe.lib.component.rest_response.error

import jp.co.biglobe.lib.http.ResponseEntityFactory
import jp.co.biglobe.lib.plugin.rest_responce.error.RestHasErrorListResponse
import jp.co.biglobe.lib.plugin.rest_view.RestJsonErrorTemplate
import jp.co.biglobe.lib.publication.exception.errorlist.HasRestErrorList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * エラーステータスを保持した例外発生時のデフォルトレスポンス
 */
@SuppressWarnings("ALL")
@Component
public final class HasRestErrorListResponseImpl implements RestHasErrorListResponse {

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
    ResponseEntity<Map> build(HasRestErrorList e) {
        Map body = restJsonErrorTemplate.build(["details": e.getErrorList()], e as Throwable)

        return responseEntityFactory.createBuilder().buildByStatusAndBody(e.getHttpStatus(), body)
    }
}

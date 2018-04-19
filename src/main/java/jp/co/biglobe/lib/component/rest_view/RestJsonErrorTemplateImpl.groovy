package jp.co.biglobe.lib.component.rest_view

import jp.co.biglobe.lib.component.view.ResponseLogger
import jp.co.biglobe.lib.component.view.jsonconvertible.JsonConvertibleVerifier
import jp.co.biglobe.lib.component.view.jsonconvertible.NotJsonConvertibleException
import jp.co.biglobe.lib.component.view.jsonconvertible.NotJsonConvertibleTemplate
import jp.co.biglobe.lib.danger.view.ErrorElement
import jp.co.biglobe.lib.plugin.rest_view.RestJsonErrorTemplate
import jp.co.biglobe.lib.publication.exception.errorstatus.HasHttpErrorStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@SuppressWarnings("ALL")
@Component
public final class RestJsonErrorTemplateImpl implements RestJsonErrorTemplate {

    @Autowired
    private RestResponseCreator responseCreator

    @Autowired
    private ResponseLogger responseLogger

    @Autowired
    private JsonConvertibleVerifier jsonConvertibleVerifier

    @Autowired
    private NotJsonConvertibleTemplate notJsonConvertibleTemplate

    /**
     * JSONエラーテンプレートの生成（ステータスコード異常）
     *
     * @param template テンプレート
     * @param throwable 例外
     * @return JsonレスポンスのMap
     */
    public Map build(final Map template, final Throwable exception) {
        Map verifiedResponse = createErrorResponseAndVerify(template, exception)
        responseLogger.log(verifiedResponse)
        return verifiedResponse
    }

    /**
     * JSON構築中に例外がスローされないかチェックし、JSONエラーテンプレートの生成（ステータスコード異常）
     *
     * @param template テンプレート
     * @param throwable 例外
     * @return JsonレスポンスのMap
     */
    private Map createErrorResponseAndVerify(final Map template, final Throwable exception) {
        Map response = createErrorResponse(template, exception)
        try {
            jsonConvertibleVerifier.verify(response)
            return response
        } catch (NotJsonConvertibleException e) {
            return createErrorResponse(notJsonConvertibleTemplate.build(e), exception)
        }
    }

    /**
     * エラーAPIレスポンス作成
     *
     * @param template テンプレート
     * @param throwable 例外
     * @return JsonレスポンスのMap
     */
    private Map createErrorResponse(final Map template, final Throwable exception) {
        Map map = ErrorElement.INSTANCE.merge(
                responseCreator.create(template),
                exception
        )
        if (exception instanceof HasHttpErrorStatus) {
            setErrorDetail(map, exception as HasHttpErrorStatus)
        }
        return map
    }

    private static final ERROR_DETAIL_FIELD_NAME = "detail"

    /**
     * エラー時detailをセットする
     * @param map セット先Map
     * @param exception 例外
     */
    private void setErrorDetail(Map map, final HasHttpErrorStatus exception) {
        if (exception.getErrorType() != null) {
            map.put(ERROR_DETAIL_FIELD_NAME, [
                    "type": exception.getErrorType().getValue()
            ])
        }
    }

}

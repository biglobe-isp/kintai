package jp.co.biglobe.lib.component.rest_view

import jp.co.biglobe.lib.component.view.ResponseLogger
import jp.co.biglobe.lib.component.view.jsonconvertible.JsonConvertibleVerifier
import jp.co.biglobe.lib.component.view.jsonconvertible.NotJsonConvertibleException
import jp.co.biglobe.lib.component.view.jsonconvertible.NotJsonConvertibleTemplate
import jp.co.biglobe.lib.danger.view.RestSystemErrorElement
import jp.co.biglobe.lib.plugin.rest_view.RestJsonSystemErrorTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@SuppressWarnings("ALL")
@Component
public final class RestJsonSystemErrorTemplateImpl implements RestJsonSystemErrorTemplate {

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
    public Map build(final Map template) {
        Map verifiedResponse = createErrorResponseAndVerify(template)
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
    private Map createErrorResponseAndVerify(final Map template) {
        Map response = createErrorResponse(template)
        try {
            jsonConvertibleVerifier.verify(response)
            return response
        } catch (NotJsonConvertibleException e) {
            return createErrorResponse(notJsonConvertibleTemplate.build(e))
        }
    }

    /**
     * エラーAPIレスポンス作成
     *
     * @param template テンプレート
     * @return JsonレスポンスのMap
     */
    private Map createErrorResponse(final Map template) {
        return RestSystemErrorElement.INSTANCE.merge(
                responseCreator.create(template)
        )
    }
}

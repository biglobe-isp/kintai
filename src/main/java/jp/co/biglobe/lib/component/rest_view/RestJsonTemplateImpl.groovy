package jp.co.biglobe.lib.component.rest_view

import jp.co.biglobe.lib.component.view.ResponseLogger
import jp.co.biglobe.lib.plugin.rest_view.RestJsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@SuppressWarnings("ALL")
@Component
public final class RestJsonTemplateImpl implements RestJsonTemplate {
    @Autowired
    private RestResponseCreator restResponseCreator

    @Autowired
    private ResponseLogger responseLogger

    /**
     * JSONテンプレートの生成（ステータスコード正常）
     *
     * @param template テンプレート
     * @return JsonレスポンスのMap
     */
    public Map build(final Map template) {
        Map response = restResponseCreator.create(template)
        responseLogger.log(response)
        return response
    }

}

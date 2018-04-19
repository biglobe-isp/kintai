package jp.co.biglobe.lib.component.rest_view;

import jp.co.biglobe.lib.danger.view.NullIgnoreJsonBuilder;
import jp.co.biglobe.lib.danger.view.RestHeaderElement;
import jp.co.biglobe.lib.plugin.event.RequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

//isp-libraryと名前が重複しないように変更
@Component
public final class RestResponseCreator {

    @Autowired
    private RequestEvent requestEvent;

    /**
     * APIレスポンス作成
     *
     * @param template テンプレート
     * @return JsonレスポンスのMap
     */
    public Map create(final Map template) {
        return RestHeaderElement.INSTANCE.merge(
                NullIgnoreJsonBuilder.INSTANCE.json(template),
                requestEvent.getRequestId()
        );
    }
}


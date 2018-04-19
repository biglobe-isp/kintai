package jp.co.biglobe.lib.plugin.rest_view

@SuppressWarnings("ALL")
public interface RestJsonTemplate {
    /**
     * JSONテンプレートの生成（ステータスコード正常）
     *
     * @param template テンプレート
     * @return JsonレスポンスのMap
     */
    public Map build(final Map template)
}

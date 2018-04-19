package jp.co.biglobe.lib.plugin.rest_view

@SuppressWarnings("ALL")
public interface RestJsonSystemErrorTemplate {
    /**
     * JSONシステムエラーテンプレートの生成（ステータスコード異常）
     *
     * @param template テンプレート
     * @param throwable 例外
     * @return JsonレスポンスのMap
     */
    public Map build(final Map template)
}

package jp.co.biglobe.lib.plugin.rest_view

@SuppressWarnings("ALL")
public interface RestJsonErrorTemplate {
    /**
     * JSONエラーテンプレートの生成（ステータスコード異常）
     *
     * @param template テンプレート
     * @param throwable 例外
     * @return JsonレスポンスのMap
     */
    public Map build(final Map template, final Throwable exception)
}

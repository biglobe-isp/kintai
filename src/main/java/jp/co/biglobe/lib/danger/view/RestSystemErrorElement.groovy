package jp.co.biglobe.lib.danger.view
/**
 * APIレスポンスのヘッダー要素
 */
@SuppressWarnings("ALL")
enum RestSystemErrorElement {
    INSTANCE

    /**
     * 予約キー名（アプリケーション開発者は、このキー名を使用不可）
     */
    private static final String RESERVED_KEY_ERROR = "error"

    /**
     * テンプレートにAPIレスポンスにエラー部を追加
     *
     * @param template テンプレート
     * @param throwable 例外
     * @return エラー部を追加したテンプレート
     */
    Map merge(Map template) {
        // 予約キー名が使われてないかチェック
        ReservedKeyVerifier.verify(RESERVED_KEY_ERROR, template)

        // API共通のレスポンスをセット
        template.put(RESERVED_KEY_ERROR, createError())
        return template
    }

    /**
     * エラー部の生成
     *
     * @param throwable 例外
     * @return エラー部
     */
    private Map createError() {
        return [
                "message": "システムエラーが発生しました",
        ]
    }

    /**
     * 予約キー名が、テンプレートで使われてないかチェックし、使われていたら例外をスロー
     *
     * @param template
     * @throws RuntimeException
     */
    void verify(final Map template) {
        ReservedKeyVerifier.verify(RESERVED_KEY_ERROR, template)
    }
}
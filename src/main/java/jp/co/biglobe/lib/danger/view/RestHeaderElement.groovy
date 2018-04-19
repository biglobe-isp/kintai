package jp.co.biglobe.lib.danger.view
/**
 * APIレスポンスのヘッダー要素
 */
@SuppressWarnings("ALL")
enum RestHeaderElement {
    INSTANCE

    /**
     * 予約キー名（アプリケーション開発者は、このキー名を使用不可）
     */
    private static final String RESERVED_KEY_HEADER = "header"

    /**
     * テンプレートにAPIレスポンスヘッダーを追加
     *
     * @param template テンプレート
     * @param requestId リクエストID
     * @return APIレスポンスヘッダーを追加したテンプレート
     */
    Map merge(Map template, final String requestId) {
        // 予約キー名が使われてないかチェック
        ReservedKeyVerifier.verify(RESERVED_KEY_HEADER, template)

        // API共通のレスポンスをセット
        template.put(RESERVED_KEY_HEADER, createHeader(requestId))
        return template
    }

    /**
     * APIレスポンスのヘッダー情報の生成（全API共通）
     *
     * @param requestId リクエストID
     * @return APIレスポンスヘッダー
     */
    private static Map createHeader(final String requestId) {
        return [
                "requestId": requestId,
        ]
    }

    /**
     * 予約キー名が、テンプレートで使われてないかチェックし、使われていたら例外をスロー
     *
     * @param template
     * @throws RuntimeException
     */
    void verify(final Map template) {
        ReservedKeyVerifier.verify(RESERVED_KEY_HEADER, template)
    }
}
package jp.co.biglobe.lib.publication.exception.errorstatus;

/**
 * エラーステータス
 */
public interface RestErrorStatus {

    /**
     * エラーステータスを取得
     *
     * @return エラーステータス
     */
    String getStatus();

    /**
     * エラーステータスの日本語メッセージを取得
     *
     * @return エラーステータスの日本語メッセージ
     */
    String getMessage();

}

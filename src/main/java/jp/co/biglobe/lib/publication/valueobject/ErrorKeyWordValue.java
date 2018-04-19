package jp.co.biglobe.lib.publication.valueobject;

/**
 * エラーメッセージ用 出力値
 */
public interface ErrorKeyWordValue {

    String getValue();

    default String getErrorMessageValue() {
        return getClass().getSimpleName() + "=" + getValue();
    }

}

package com.kintai.exception;

/**
 * バリデーション例外用のException
 */
public class ValidatorException extends Exception {
    // warningを回避するための宣言
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * 引数の例外メッセージを親クラス{@link Exception}に連携します。
     * @param message 例外メッセージ
     */
    public ValidatorException(String message) {
        super(message);
    }
}

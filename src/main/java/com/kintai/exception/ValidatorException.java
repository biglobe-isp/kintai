package com.kintai.exception;

/**
 * 検証例外用のException
 */
public class ValidatorException extends Exception {
    //warningを回避するための宣言
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @param message エラーメッセージ
     */
    public ValidatorException(String message) {
        super(message);
    }
}

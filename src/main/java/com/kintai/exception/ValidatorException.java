package com.kintai.exception;

public class ValidatorException extends Exception {
    //warningを回避するための宣言
    private static final long serialVersionUID = 1L;

    public ValidatorException(String message) {
        super(message);
    }
}

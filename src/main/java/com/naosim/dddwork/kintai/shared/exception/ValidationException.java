package com.naosim.dddwork.kintai.shared.exception;

import com.google.common.collect.ImmutableList;


public class ValidationException extends RuntimeException {

    final ImmutableList<String> errorMessages;

    public ValidationException(String message, ImmutableList<String> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }


    public ImmutableList<String> getErrorMessages() {
        return errorMessages;
    }
}

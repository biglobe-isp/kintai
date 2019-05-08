package com.naosim.dddwork.kintai.api;

import java.util.Arrays;
import java.util.List;

public class ArgumentParser {

    private final List<String> _arguments;

    public ArgumentParser(String[] args) {

        if(args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        _arguments = Arrays.asList(args);
    }

    public Request pickRequest() {

        final String requestString = _arguments.get(0);

        try {
            return Request.valueOf(requestString.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            final String validRequestValues = Request.validRequestValues();
            throw new IllegalArgumentException(
                    String.format("リクエストの指定ミスです。[入力値=%s, 有効なリクエスト値=%s]", requestString, validRequestValues), e);
        }
    }
}


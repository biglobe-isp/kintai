package com.naosim.dddwork.kintai.api;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.kintai.api.request.Request;
import com.naosim.dddwork.kintai.api.request.RequestOperands;

import java.util.Arrays;


/**
 * 引数解析機
 */
public class ArgumentParser {

    private final ImmutableList<String> _arguments;
    private final ImmutableList<String> _operands;

    public ArgumentParser(String[] args) {

        if(args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        _arguments = ImmutableList.copyOf(Arrays.asList(args));
        _operands = ImmutableList.copyOf(_arguments.subList(1, _arguments.size()));
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

    public RequestOperands pickOperands() {

        return new RequestOperands(_operands);
    }
}


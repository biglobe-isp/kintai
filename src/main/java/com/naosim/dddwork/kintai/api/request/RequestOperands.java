package com.naosim.dddwork.kintai.api.request;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.kintai.api.request.protocol.RequestOperandVerifiable;
import lombok.ToString;


/**
 * リクエストオペランド（必須引数）たち
 */
@ToString
public class RequestOperands {

    final ImmutableList<String> operands;

    public RequestOperands(ImmutableList<String> operands) {
        this.operands = operands;
    }


    public boolean isSufficientFor(RequestOperandVerifiable target) {
        return operands.size() >= target.numberOfRequired();
    }

    public boolean isNotSufficientFor(RequestOperandVerifiable target) {
        return !isSufficientFor(target);
    }

    public String at(int index) {
        return operands.get(index);
    }
}

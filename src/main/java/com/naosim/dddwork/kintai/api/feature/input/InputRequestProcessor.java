package com.naosim.dddwork.kintai.api.feature.input;

import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.api.request.protocol.RequestProcessor;
import com.naosim.dddwork.kintai.service.command.DailyWorkTimeRegistration;


/**
 * ［指定日の勤怠登録］リクエストプロセッサー
 */
public class InputRequestProcessor implements RequestProcessor {

    @Override
    public void execute(RequestOperands operands) {

        InputRequestOperandParser parser = InputRequestOperandParser.of(operands);
        DailyWorkTimeRegistration.Parameter parameter = parser.serviceParameter();

        final DailyWorkTimeRegistration service = new DailyWorkTimeRegistration();
        service.execute(parameter);
    }
}

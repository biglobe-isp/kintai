package com.naosim.dddwork.kintai.api.feature.total;

import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.api.request.protocol.RequestProcessor;
import com.naosim.dddwork.kintai.service.query.MonthlyTotalWorkedTimeQuery;


/**
 * ［指定月の勤務時間合計表示］リクエストプロセッサー
 */
public class TotalRequestProcessor implements RequestProcessor {

    @Override
    public void execute(RequestOperands operands) {

        TotalRequestOperandParser parser = TotalRequestOperandParser.of(operands);
        MonthlyTotalWorkedTimeQuery.Parameter parameter = parser.serviceParameter();

        final MonthlyTotalWorkedTimeQuery service = new MonthlyTotalWorkedTimeQuery();
        service.execute(parameter);
    }
}

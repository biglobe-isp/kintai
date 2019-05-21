package com.naosim.dddwork.kintai.api.feature.total;

import com.naosim.dddwork.kintai.api.port.output.MonthlyTotalWorkedTimeOutputPort;
import com.naosim.dddwork.kintai.api.port.protocol.OutputPort;
import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.api.request.protocol.RequestProcessor;
import com.naosim.dddwork.kintai.settings.DataStorePolicy;
import com.naosim.dddwork.kintai.datasource.timerecord.protocol.DataSourceFactory;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;
import com.naosim.dddwork.kintai.service.query.MonthlyTotalWorkedTimeQueryService;


/**
 * ［指定月の勤務時間合計表示］リクエストプロセッサー
 */
public class TotalRequestProcessor implements RequestProcessor {

    final MonthlyTotalWorkedTimeQueryService service;


    public TotalRequestProcessor(DataStorePolicy dataStorePolicy) {

        final DataSourceFactory dataSourceFactory = dataStorePolicy.dataSourceFactory();
        service = new MonthlyTotalWorkedTimeQueryService(dataSourceFactory.workedTimeRepository());
    }


    @Override
    public void execute(RequestOperands operands) {

        TotalRequestOperandParser parser = TotalRequestOperandParser.of(operands);
        MonthlyTotalWorkedTimeQueryService.Parameter parameter = parser.serviceParameter();

        final MonthlyTotalWorkedTime monthlyTotalWorkedTime = service.execute(parameter);
        final OutputPort outputPort = new MonthlyTotalWorkedTimeOutputPort();
        outputPort.show(monthlyTotalWorkedTime);
    }
}

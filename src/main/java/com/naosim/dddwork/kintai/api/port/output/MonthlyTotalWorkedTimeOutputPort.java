package com.naosim.dddwork.kintai.api.port.output;

import com.naosim.dddwork.kintai.api.port.protocol.OutputPort;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;


/**
 * ［指定月の勤務時間合計表示］ポート
 */
public class MonthlyTotalWorkedTimeOutputPort implements OutputPort<MonthlyTotalWorkedTime> {

    @Override
    public void show(MonthlyTotalWorkedTime model) {
        model.showOn(System.out);
    }
}

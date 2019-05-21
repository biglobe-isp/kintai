package com.naosim.dddwork.kintai.api.port.output;

import com.naosim.dddwork.kintai.api.port.protocol.OutputPort;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;


/**
 * ［指定月の勤務時間合計表示］ポート
 */
public class MonthlyTotalWorkedTimeOutputPort implements OutputPort<MonthlyTotalWorkedTime> {

    @Override
    public void show(MonthlyTotalWorkedTime model) {

        {
            final int hour = model.getTotalPaidWorkMinutes() / 60;
            final int minute = model.getTotalPaidWorkMinutes() % 60;
            final String content = String.format("勤務時間: %d時間%d分", hour, minute);
            System.out.println(content);
        }

        {
            final int hour = model.getTotalOvertimeMinutes() / 60;
            final int minute = model.getTotalOvertimeMinutes() % 60;
            final String content = String.format("残業時間: %d時間%d分", hour, minute);
            System.out.println(content);
        }
    }
}

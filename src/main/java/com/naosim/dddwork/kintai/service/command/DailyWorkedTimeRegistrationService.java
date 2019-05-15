package com.naosim.dddwork.kintai.service.command;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.BeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.EndTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;
import lombok.AllArgsConstructor;
import lombok.Value;


/**
 * ［指定日の勤怠登録］サービス
 */
public class DailyWorkedTimeRegistrationService {

    @Value
    @AllArgsConstructor(staticName="of")
    public static class Parameter {

        final AttendanceDate attendanceDate;
        final BeginTime beginTime;
        final EndTime endTime;
    }

    final WorkedTimeRepository repository;


    public DailyWorkedTimeRegistrationService(WorkedTimeRepository repository) {
        this.repository = repository;
    }


    public void execute(Parameter parameter) {
        _registerWorkTime(parameter);
    }

    private void _registerWorkTime(Parameter parameter) {


//NOTE: ここから勤務時間と残業時間の算出をやっている
        int startH = parameter.beginTime.hourRawValue();
        int startM = parameter.beginTime.minuteRawValue();

        int endH = parameter.endTime.hourRawValue();
        int endM = parameter.endTime.minuteRawValue();

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if(endH == 12) {
            workMinutes -= endM;
        } else if(endH >= 13) {
            workMinutes -= 60;
        }

        if(endH == 18) {
            workMinutes -= endM;
        } else if(endH >= 19) {
            workMinutes -= 60;
        }

        if(endH == 21) {
            workMinutes -= endM;
        } else if(endH >= 22) {
            workMinutes -= 60;
        }

        if (endH > 23) {
            final int serviceH = endH - 24;
            final int serviceM = endM;
            workMinutes -= serviceH * 60 + serviceM;
        }

        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);

        DailyWorkedTime dailyWorkedTime = DailyWorkedTime.of(
                parameter.attendanceDate,
                parameter.beginTime,
                parameter.endTime,
                AmountOfMinutes.of(workMinutes),
                AmountOfMinutes.of(overWorkMinutes));

        repository.save(dailyWorkedTime);
    }

}

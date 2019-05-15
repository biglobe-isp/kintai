package com.naosim.dddwork.kintai.service.command;

import com.naosim.dddwork.kintai.domain.core.type.time.component.TimeOfDay;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.format.DateTimeFormatter;


/**
 * ［指定日の勤怠登録］サービス
 */
public class DailyWorkedTimeRegistrationService {

    @Value
    @AllArgsConstructor(staticName="of")
    public static class Parameter {

        final AttendanceDate attendanceDate;
        final TimeOfDay beginTime;
        final TimeOfDay endTime;
    }

    final WorkedTimeRepository repository;


    public DailyWorkedTimeRegistrationService(WorkedTimeRepository repository) {
        this.repository = repository;
    }


    public void execute(Parameter parameter) {
        _registerWorkTime(parameter);
    }

    private void _registerWorkTime(Parameter parameter) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = parameter.attendanceDate.getLocalDate().format(formatter);

        String start = pad(parameter.beginTime.getHour().getValue(), 2, "0")
                     + pad(parameter.beginTime.getMinute().getValue(), 2, "0");
        String end = pad(parameter.endTime.getHour().getValue(), 2, "0")
                   + pad(parameter.endTime.getMinute().getValue(), 2, "0");

        int startH = parameter.beginTime.getHour().getValue();
        int startM = parameter.beginTime.getMinute().getValue();

        int endH = parameter.endTime.getHour().getValue();
        int endM = parameter.endTime.getMinute().getValue();

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

        DailyWorkedTime dailyWorkedTime = DailyWorkedTime.of(date, start, end, workMinutes, overWorkMinutes);
        repository.save(dailyWorkedTime);
    }

    //TODO: 暫定的に設けている
    public static String pad(int value, int length,  CharSequence replacement){

        return String.format("%" + length + "d", value).replace(" ", replacement);
    }
}

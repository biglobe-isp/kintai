package com.naosim.dddwork.kintai.service.command;

import com.naosim.dddwork.kintai.domain.core.type.time.component.TimeOfDay;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.shared.exception.SystemException;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * ［指定日の勤怠登録］サービス
 */
public class DailyWorkTimeRegistration {

    @Value
    @AllArgsConstructor(staticName="of")
    public static class Parameter {

        final AttendanceDate attendanceDate;
        final TimeOfDay beginTime;
        final TimeOfDay endTime;
    }

    public void execute(Parameter parameter) {

        try {
            _registerWorkTime(parameter);
        }
        catch (IOException e) {
            throw new SystemException("日次勤怠登録処理中に入出力例外が発生しました．", e);
        }
    }

    private static void _registerWorkTime(Parameter parameter) throws IOException {

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
        File file = new File("data.csv");
        String now = LocalDateTime.now().toString();
        try(FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
        }
    }

    //TODO: 暫定的に設けている
    public static String pad(int value, int length,  CharSequence replacement){

        return String.format("%" + length + "d", value).replace(" ", replacement);
    }
}

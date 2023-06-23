package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkTime;
import jp.co.biglobe.isp.kintai.domain.input_worktime.InputWorkTime;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
public class InputWorkTimeForm {
    LocalDate workDate;
    LocalTime openingTime;
    LocalTime closingTime;

    public InputWorkTimeForm(String[] args) {
        var workDateStr = args[1];
        var openingTimeStr = args[2];
        var closingTimeStr = args[3];

        workDate = LocalDate.parse(workDateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        openingTime = LocalTime.parse(openingTimeStr, DateTimeFormatter.ofPattern("HHmm"));
        closingTime = LocalTime.parse(closingTimeStr, DateTimeFormatter.ofPattern("HHmm"));
    }

    public InputWorkTime toDomain() {
        var workTime = new WorkTime(openingTime, closingTime);
        return new InputWorkTime(workDate, workTime);
    }
}

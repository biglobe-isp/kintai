package jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
@RequiredArgsConstructor
public class BreakTime {
    LocalTime start;
    LocalTime end;

    public static BreakTime create(String start, String end) {
        try {
            return new BreakTime(
                    LocalTime.parse(start, DateTimeFormatter.ofPattern("HHmm")),
                    LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"))
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("出退勤時刻が不正値です。");
        }
    }

    public int getStartMinutes() {
        return start.getHour() * 60 + start.getMinute();
    }

    public int getEndMinutes() {
        return end.getHour() * 60 + end.getMinute();
    }
}

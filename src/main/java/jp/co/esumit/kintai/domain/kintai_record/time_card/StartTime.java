package jp.co.esumit.kintai.domain.kintai_record.time_card;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
@RequiredArgsConstructor
public class StartTime {
    LocalTime startTime;

    public StartTime(String time) {
        try {
            this.startTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
        } catch (Exception e) {
            throw new IllegalArgumentException("開始時刻が不正値です。");
        }
    }

    public String toString() {
        return startTime.toString().replace(":", "");
    }

    public int getMinutes() {
        return startTime.getHour() * 60 + startTime.getMinute();
    }
}

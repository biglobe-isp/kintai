package jp.co.esumit.kintai.domain.kintai_record.time_card;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
@RequiredArgsConstructor
public class EndTime {
    LocalTime endTime;

    public EndTime(String time) {
        try {
            this.endTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
        } catch (Exception e) {
            throw new IllegalArgumentException("終了時刻が不正値です。");
        }
    }

    public String toString() {
        return endTime.toString().replace(":", "");
    }

    public int getMinutes() {
        return endTime.getHour() * 60 + endTime.getMinute();
    }
}

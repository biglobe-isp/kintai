package jp.co.esumit.kintai.domain.kintai_record.target_day;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Value
public class TargetDay {
    LocalDate value;

    public TargetDay(String value) {

        try {
            this.value = LocalDate.parse(value, DateTimeFormatter.ofPattern("uuuuMMdd"));
        } catch (Exception e) {
            throw new IllegalArgumentException("対象日付が不正値です。");
        }
    }

    public String toString(DateTimeFormatter format) {
        return value.format(format);
    }
}


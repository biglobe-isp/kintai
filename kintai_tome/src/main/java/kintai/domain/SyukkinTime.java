package kintai.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SyukkinTime {

    private String value;

    public SyukkinTime(String value) {
        this.value = value;
    }

    public LocalTime getLocalTimeValue() {

        return LocalTime.parse(value, DateTimeFormatter.ofPattern("HHmm"));
    }

    public String getValue() {
        return value;
    }
}

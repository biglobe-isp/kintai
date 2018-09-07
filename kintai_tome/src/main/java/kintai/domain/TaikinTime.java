package kintai.domain;

import java.time.LocalTime;

public class TaikinTime {

    private String value;

    public TaikinTime(String value) {
        this.value = value;
    }

    public LocalTime getLocalTimeValue() {

        int hour = Integer.valueOf(value.substring(0,2));
        int minutes = Integer.valueOf(value.substring(2,4));

        return LocalTime.of(hour, minutes);
    }

    public String getValue() {
        return value;
    }
}

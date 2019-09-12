package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class StartTime {

    @Getter
    private final LocalTime value;

    public static StartTime of(int hour, int minute) {
        return new StartTime(LocalTime.of(hour, minute));
    }

    public static StartTime parse(String text, DateTimeFormatter formatter) {
        return new StartTime(LocalTime.parse(text, formatter));
    }

    public String format(DateTimeFormatter formatter) {
        return value.format(formatter);
    }
}

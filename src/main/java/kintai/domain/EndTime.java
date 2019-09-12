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
public class EndTime {

    @Getter
    private final LocalTime value;

    public static EndTime of(int hour, int minute) {
        return new EndTime(LocalTime.of(hour, minute));
    }

    public static EndTime parse(String text, DateTimeFormatter formatter) {
        return new EndTime(LocalTime.parse(text, formatter));
    }

    public String format(DateTimeFormatter formatter) {
        return value.format(formatter);
    }
}

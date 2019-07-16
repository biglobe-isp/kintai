package refactor.domain;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public class WorkingDay {
    private final int year;
    private final int month;
    private final int day;

    public WorkingDay(String yyyymmdd) {
        year = Integer.valueOf(yyyymmdd.substring(0, 4));
        month = Integer.valueOf(yyyymmdd.substring(4, 6));
        day = Integer.valueOf(yyyymmdd.substring(6, 8));
    }

    public String inYYYMMDD() {
        return String.format("%d%02d%02d", year, month, day);
    }
}

package kintai.domain.company;

import java.time.LocalTime;

public class SyugyoTime {

    private final LocalTime value = LocalTime.of(18, 0);

    public LocalTime getValue() {
        return value;
    }

}

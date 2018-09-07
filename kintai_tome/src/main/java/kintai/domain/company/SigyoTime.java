package kintai.domain.company;

import java.time.LocalTime;

public class SigyoTime {

    private final LocalTime value = LocalTime.of(9, 0);

    public LocalTime getValue() {
        return value;
    }

}

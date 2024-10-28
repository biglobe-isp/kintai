package com.naosim.dddwork.domain.value_object;

import java.time.LocalTime;

public class Time {
    private final LocalTime value;
    public Time(LocalTime value) {
        this.value = value;
    }
    public LocalTime getValue() {return value;}
}

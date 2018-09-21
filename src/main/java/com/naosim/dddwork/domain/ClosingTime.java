package com.naosim.dddwork.domain;

import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClosingTime {
    @Getter
    private final LocalTime value;

    public ClosingTime (String closingTime) {
        this.value = LocalTime.parse(closingTime, DateTimeFormatter.ofPattern("HH:mm"));
    }
}

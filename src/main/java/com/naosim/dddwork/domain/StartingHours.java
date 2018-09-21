package com.naosim.dddwork.domain;

import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StartingHours {
    @Getter
    private final LocalTime value;

    public StartingHours (String startingHours) {
        this.value = LocalTime.parse(startingHours, DateTimeFormatter.ofPattern("HH:mm"));
    }
}

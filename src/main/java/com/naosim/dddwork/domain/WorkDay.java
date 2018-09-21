package com.naosim.dddwork.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkDay {
    @Getter
    private final LocalDate value;

    public WorkDay (String workDay) {
        this.value = LocalDate.parse(workDay, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}

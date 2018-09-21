package com.naosim.dddwork.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkingMonth {
    @Getter
    private final String value;

    public WorkingMonth (String workingMonth) {
        this.value = LocalDate.parse(workingMonth, DateTimeFormatter.ofPattern("yyyy/MM")).toString();
    }
}

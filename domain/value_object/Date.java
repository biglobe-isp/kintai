package com.naosim.dddwork.domain.value_object;

import java.time.LocalDate;

public class Date {
    private final LocalDate value;
    public Date(LocalDate value) {
        this.value = value;
    }
    public LocalDate getValue() {return value;}
}

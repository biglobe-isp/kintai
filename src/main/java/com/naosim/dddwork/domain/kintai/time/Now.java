package com.naosim.dddwork.domain.kintai.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
public class Now {

    @Getter
    private final LocalDateTime value;

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Now() {
        this.value = LocalDateTime.now();
    }

    public Now(String value) {
        this.value = LocalDateTime.parse(value, formatter);
    }

    @Override
    public String toString() {
        return this.value.format(formatter);
    }
}

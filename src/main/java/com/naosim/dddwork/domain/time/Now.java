package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class Now {

    @Getter
    private final String value;

    public Now() {
        this.value = LocalDateTime.now().toString();
    }

    public Now(String value) {
        this.value = value;
    }
}

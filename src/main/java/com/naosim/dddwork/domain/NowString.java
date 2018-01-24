package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class NowString {

    @Getter
    private final String value;

    public NowString() {
        this.value = LocalDateTime.now().toString();
    }

    public NowString(String value) {
        this.value = value;
    }
}

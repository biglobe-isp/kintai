package org.example.domain;

import lombok.Value;
@Value
public class Minute {
    int value;

    public Minute(int value) {
        if(value < 0 || value > 59){
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
        this.value = value;
    }

    public String getFormatMinute() {
        return String.format("%02d", value);
    }
}

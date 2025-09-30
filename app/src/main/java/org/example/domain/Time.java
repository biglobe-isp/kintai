package org.example.domain;

import lombok.ToString;
import lombok.Value;

@Value
@ToString(includeFieldNames = false)
public class Time {
    int value;

    public static Time of(int value) {
        if(value < 0){
            throw new IllegalArgumentException("Time must be non-negative");
        }
        return new Time(value);
    }
}

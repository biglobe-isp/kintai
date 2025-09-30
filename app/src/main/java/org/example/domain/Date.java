package org.example.domain;

import lombok.Value;
import lombok.ToString;

@Value
@ToString(includeFieldNames = false)
public class Date {
    String value;

    public Date(String value){
        if(value.length() != 8){
            throw new IllegalArgumentException("Date must be in YYYYMMDD format");
        }

        this.value = value;
    }
}

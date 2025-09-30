package org.example.domain;

import lombok.Value;
import lombok.ToString;
import java.time.LocalDateTime;


@Value
@ToString(includeFieldNames = false)
public class Timestamp {
    LocalDateTime value;

    public static Timestamp of(String value){
        if(value == null || value.isEmpty()){
            throw new IllegalArgumentException("Timestamp cannot be null or empty");
        }

        try{
            LocalDateTime dateTime = LocalDateTime.parse(value);
            return new Timestamp(dateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Timestamp must be in ISO-8601 format: " + e.getMessage());
        }
    }
}

package org.example.domain;

import lombok.Value;

@Value
public class Month {
    String value;

    public Month(String value){
        if(value == null){
            throw new IllegalArgumentException("Month value cannot be null");
        }

        if(!value.matches("\\d{6}")){
            throw new IllegalArgumentException("Month value must be in the format YYYYMM");
        }

        this.value = value;
    }

    public Month DatetoMonth(Date date){
        return new Month(date.getValue().substring(0, 6));
    }
}

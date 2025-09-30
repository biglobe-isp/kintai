package org.example.domain;

import lombok.Value;

@Value
public class EndTime {
    Hour endHours;
    Minute endMinutes;

    public static EndTime of(Hour endHours, Minute endMinutes) {
        if(endHours.getValue() == 12 || endHours.getValue() == 18 || endHours.getValue() == 21){
            return  new EndTime(endHours, new Minute(0));
        }
        else{
            return  new EndTime(endHours, endMinutes);
        }
    }

    public Time convertToMinutes() {
        return new Time(endHours.getValue() * 60 + endMinutes.getValue());
    }
}

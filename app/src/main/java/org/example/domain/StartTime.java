package org.example.domain;

import lombok.Value;

@Value
public class StartTime {
    Hour startHours;
    Minute startMinutes;

    public static StartTime of(Hour startHours, Minute startMinutes) {
        if(startHours.getValue() == 12 || startHours.getValue() == 18 || startHours.getValue() == 21){
            return  new StartTime(new Hour(startHours.getValue() + 1), new Minute(0));
        }
        else{
            return  new StartTime(startHours, startMinutes);
        }
    }

    public Time convertToMinutes() {
        return new Time(startHours.getValue() * 60 + startMinutes.getValue());
    }
}

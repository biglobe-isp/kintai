package kintai.domain;


import lombok.Value;

import java.util.List;
@Value
public class LaborRegulations {
    public static final LaborRegulations DEFAULT = new LaborRegulations("09:00:00", "18:00:00", List.of(new RestTime("12:00:00", "13:00:00"), new RestTime("18:00:00", "19:00:00"), new RestTime("21:00:00", "22:00:00")));
    String openingTheTime;
    String closingTheTime;
    List<RestTime> restTimes;

    public int calcRestTimeMinutes(WorkStart workStart, WorkEnd workEnd){
        int restTimeMinutes = 0;
        for(RestTime restTime : getRestTimes()){
            int restStartH = restTime.getStartLocalTime().getHour();
            int restEndH   = restTime.getEndLocalTime().getHour();

            if(workEnd.getLocalTime().getHour() == restStartH){
                restTimeMinutes += workEnd.getLocalTime().getMinute();
            }else if(workEnd.getLocalTime().getHour() >= restEndH){
                restTimeMinutes += 60;
            }
        }
        return restTimeMinutes;
    }





}

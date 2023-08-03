package kintai.domain;


import lombok.Value;

import java.util.List;
@Value
public class LaborRegulations {
    public static final LaborRegulations DEFAULT = new LaborRegulations("09:00:00","18:00:00", List.of(new RestTime("12:00:00","13:00:00"),new RestTime("18:00:00","19:00:00"),new RestTime("21:00:00","22:00:00")));
    String openingTheTime;
    String closingTheTime;
    List<RestTime> restTimes;

    public int calcRestTimeMinutes(int startH,int startM, int endH,int endM){
        int restTimeMinutes = 0;
        for(RestTime restTime : getRestTimes()){
            int restStartH = Integer.parseInt(restTime.getStart().substring(0,2));
            int restEndH   = Integer.parseInt(restTime.getEnd().substring(0,2));
            if(endH == restStartH){
                restTimeMinutes += endM;
            }else if(endH >= restEndH){
                restTimeMinutes += 60;
            }
        }
        return restTimeMinutes;
    }





}

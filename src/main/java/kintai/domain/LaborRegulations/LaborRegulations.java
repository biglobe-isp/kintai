package kintai.domain.LaborRegulations;


import kintai.domain.InputAttendance.WorkEnd;
import kintai.domain.InputAttendance.WorkStart;
import lombok.Value;

import java.util.List;
@Value
public class LaborRegulations {
    public static final LaborRegulations DEFAULT = new LaborRegulations("09:00:00", "18:00:00", List.of(new RestTimeRange("12:00:00", "13:00:00"), new RestTimeRange("18:00:00", "19:00:00"), new RestTimeRange("21:00:00", "22:00:00")));
    String openingTheTime;
    String closingTheTime;
    List<RestTimeRange> restTimes;

    public int calcrestTimeDuration(WorkStart workStart, WorkEnd workEnd){
        int restTimeDuration = 0;
        for(RestTimeRange restTimeRange : getRestTimes()){
            int restStartH = restTimeRange.getStartLocalTime().getHour();
            int restEndH   = restTimeRange.getEndLocalTime().getHour();

            if(workEnd.getLocalTime().getHour() == restStartH){
                restTimeDuration += workEnd.getLocalTime().getMinute();
            }else if(workEnd.getLocalTime().getHour() >= restEndH){
                restTimeDuration += 60;
            }
        }
        return restTimeDuration;
    }





}

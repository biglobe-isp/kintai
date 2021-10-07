package domain;

import lombok.AllArgsConstructor;

public class OverWorkTime {
    private String value;

    public OverWorkTime calculate(WorkStartTime workStartTime, WorkEndTime workEndTime){
         Integer startH = workStartTime.startH;
         Integer startM = workStartTime.startM;
         Integer endH = workEndTime.endH;
         Integer endM = workEndTime.endM;
        Integer workMinutes = endH * 60 + endM - (startH * 60 + startM);

        Integer overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
        value = overWorkMinutes.toString();
        return new OverWorkTime();
    }
}
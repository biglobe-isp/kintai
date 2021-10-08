package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class WorkTime {
    @Getter
    private Integer value;

    public WorkTime calculate(WorkStartTime workStartTime, WorkEndTime workEndTime){
        int startH = workStartTime.getStartH();
        int startM = workStartTime.getStartM();
        int endH = workEndTime.getEndH();
        int endM = workEndTime.getEndM();
        Integer workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }

        value = workMinutes;
        return new WorkTime(value);
    }
}


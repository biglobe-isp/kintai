package domain;

import lombok.Getter;

public class WorkTime {
    @Getter
    private Integer value;

    public WorkTime(int i){
        this.value = i;
    }

    public WorkTime calculate(WorkStartTime workStartTime, WorkEndTime workEndTime){

        String start = workStartTime.value.toString();
        String end = workEndTime.value.toString();
        int startH = Integer.valueOf(start.substring(0, 2));
        int startM = Integer.valueOf(start.substring(2, 4));
        int endH = Integer.valueOf(end.substring(0, 2));
        int endM = Integer.valueOf(end.substring(2, 4));
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


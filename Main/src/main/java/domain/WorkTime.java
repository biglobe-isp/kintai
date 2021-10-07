package domain;

import lombok.AllArgsConstructor;

public class WorkTime {
    private String value;

    public WorkTime calculate(WorkStartTime workStartTime, WorkEndTime workEndTime){
        int startH = workStartTime.startH;
        int startM = workStartTime.startM;
        int endH = workEndTime.endH;
        int endM = workEndTime.endM;
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

        String hoge = workMinutes.toString();
        return new WorkTime();
    }
}


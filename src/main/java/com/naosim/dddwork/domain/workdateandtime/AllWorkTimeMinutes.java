package com.naosim.dddwork.domain.workdateandtime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AllWorkTimeMinutes {
    @Getter
    private Integer value;

    private WorkDateAndTime workDateAndTime;

    public AllWorkTimeMinutes(WorkDateAndTime workDateAndTime) {
        this.workDateAndTime = workDateAndTime;
        setWorkMinutes();
    }

    private void setWorkMinutes() {

        int startH = Integer.valueOf(workDateAndTime.getWorkTimeStart().getValue().substring(0, 2));
        int startM = Integer.valueOf(workDateAndTime.getWorkTimeStart().getValue().substring(2, 4));

        int endH = Integer.valueOf(workDateAndTime.getWorkTimeEnd().getValue().substring(0, 2));
        int endM = Integer.valueOf(workDateAndTime.getWorkTimeEnd().getValue().substring(2, 4));
        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

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

        this.value = workMinutes;
    }
}

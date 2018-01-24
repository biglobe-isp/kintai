package com.naosim.dddwork.domain.time.work;

import com.naosim.dddwork.domain.time.Minute;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class WorkMinutes {

    @Getter
    private final Minute minute;

    public WorkMinutes(int value) {
        this.minute = new Minute(value);
    }

    public int getInt() {
        return this.minute.getValue();
    }

    @Override
    public String toString() {
        return this.minute.toString();
    }

    public static WorkMinutes get(WorkStartTime workStartTime, WorkEndTime workEndTime) {
        if (!isStartLessOrEqualEnd(workStartTime, workEndTime))
            throw new RuntimeException("開始時刻は終了時刻より前の時刻を設定してください");

        int workMinutesInt = workEndTime.getTime().convertTimeToMinutes() - workStartTime.getTime().convertTimeToMinutes();

        BreakMinutes breakMinutes = new BreakMinutes(workStartTime, workEndTime);
        workMinutesInt -= breakMinutes.getLunchBreakMinutes();
        workMinutesInt -= breakMinutes.getEveningBreakMinutes();
        workMinutesInt -= breakMinutes.getNightBreakMinutes();

        return new WorkMinutes(workMinutesInt);
    }

    private static boolean isStartLessOrEqualEnd(WorkStartTime workStartTime, WorkEndTime workEndTime) {
        int startMinutes = workStartTime.getTime().convertTimeToMinutes();
        int endMinutes = workEndTime.getTime().convertTimeToMinutes();

        return startMinutes <= endMinutes;
    }

}

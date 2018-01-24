package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class KintaiRegist {

    @Getter
    private final WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay;

    @Getter
    private final Time startTime;

    @Getter
    private final Time endTime;

    public KintaiRegist(WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay) {
        this.workStartAndEndTimeOfOneDay = workStartAndEndTimeOfOneDay;

        this.startTime = new Time(
                Integer.valueOf(workStartAndEndTimeOfOneDay.getStartTime().substring(0, 2)),
                Integer.valueOf(workStartAndEndTimeOfOneDay.getStartTime().substring(2, 4))
        );

        this.endTime = new Time(
                Integer.valueOf(workStartAndEndTimeOfOneDay.getEndTime().substring(0, 2)),
                Integer.valueOf(workStartAndEndTimeOfOneDay.getEndTime().substring(2, 4))
        );

        if (!this.isStartLessOrEqualEnd())
            throw new RuntimeException("開始時刻は終了時刻より前の時刻を設定してください");
    }

    public KintaiOfOneDay getKintaiOfOneDay() {
        int workMinutes = this.getWorkMinutes();

        workMinutes -= this.getLunchBreakMinutes();
        workMinutes -= this.getEveningBreakMinutes();
        workMinutes -= this.getNightBreakMinutes();

        int overWorkMinutes = this.getOverWorkMinutes(workMinutes);

        return new KintaiOfOneDay(
                this.workStartAndEndTimeOfOneDay.getDate(), this.workStartAndEndTimeOfOneDay.getStartTime(), this.workStartAndEndTimeOfOneDay.getEndTime(),
                Integer.toString(workMinutes), Integer.toString(overWorkMinutes), this.workStartAndEndTimeOfOneDay.getNow()
        );
    }

    private int getOverWorkMinutes(int workMinutes) {
        return Math.max(workMinutes - 8 * 60, 0);
    }

    private int getWorkMinutes() {
        return this.endTime.convertTimeToMinutes() - this.startTime.convertTimeToMinutes();
    }

    private int getLunchBreakMinutes() {
        return this.getBreakMinutes(12);
    }

    private int getEveningBreakMinutes() {
        return this.getBreakMinutes(18);
    }

    private int getNightBreakMinutes() {
        return this.getBreakMinutes(21);
    }

    private int getBreakMinutes(int breakStartHour) {
        if (this.endTime.getHour() == breakStartHour) {
            if (this.startTime.getHour() < breakStartHour) return this.endTime.getMinute();
            if (this.startTime.getHour() == breakStartHour)
                return this.endTime.getMinute() - this.startTime.getMinute();
        } else if (this.endTime.getHour() > breakStartHour) {
            return 60;
        }
        return 0;
    }

    private boolean isStartLessOrEqualEnd() {
        int startMinutes = this.startTime.convertTimeToMinutes();
        int endMinutes = this.endTime.convertTimeToMinutes();

        return startMinutes <= endMinutes;
    }
}

package com.naosim.dddwork.domain;


import com.naosim.dddwork.domain.time.work.OverWorkMinutes;
import com.naosim.dddwork.domain.time.work.WorkEndTime;
import com.naosim.dddwork.domain.time.work.WorkMinutes;
import com.naosim.dddwork.domain.time.work.WorkStartTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class KintaiRegist {

    @Getter
    private final WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay;

    @Getter
    private final WorkStartTime workStartTime;

    @Getter
    private final WorkEndTime workEndTime;

    public KintaiRegist(WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay) {
        this.workStartAndEndTimeOfOneDay = workStartAndEndTimeOfOneDay;

        this.workStartTime = workStartAndEndTimeOfOneDay.getWorkStartTime();
        this.workEndTime = workStartAndEndTimeOfOneDay.getWorkEndTime();

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
                this.workStartAndEndTimeOfOneDay.getWorkWorkDate(),
                this.workStartAndEndTimeOfOneDay.getWorkStartTime(),
                this.workStartAndEndTimeOfOneDay.getWorkEndTime(),
                new WorkMinutes(workMinutes),
                new OverWorkMinutes(overWorkMinutes),
                this.workStartAndEndTimeOfOneDay.getNow()
        );
    }

    private int getOverWorkMinutes(int workMinutes) {
        return Math.max(workMinutes - 8 * 60, 0);
    }

    private int getWorkMinutes() {
        return this.workEndTime.getTime().convertTimeToMinutes() - this.workStartTime.getTime().convertTimeToMinutes();
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
        if (this.workEndTime.getTime().getHour().getValue() == breakStartHour) {
            if (this.workStartTime.getTime().getHour().getValue() < breakStartHour) return this.workEndTime.getTime().getMinute().getValue();
            if (this.workStartTime.getTime().getHour().getValue() == breakStartHour)
                return this.workEndTime.getTime().getMinute().getValue() - this.workStartTime.getTime().getMinute().getValue();
        } else if (this.workEndTime.getTime().getHour().getValue() > breakStartHour) {
            return 60;
        }
        return 0;
    }

    private boolean isStartLessOrEqualEnd() {
        int startMinutes = this.workStartTime.getTime().convertTimeToMinutes();
        int endMinutes = this.workEndTime.getTime().convertTimeToMinutes();

        return startMinutes <= endMinutes;
    }
}

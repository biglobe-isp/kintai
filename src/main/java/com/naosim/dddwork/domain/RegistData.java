package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class RegistData extends ProcessData {

    @Getter
    private TimeData startTime;

    @Getter
    private TimeData endTime;

    public RegistData(InputData inputData) {
        super(inputData);
        this.setFields();
    }

    public String getOutputData() {
        int workMinutes = this.getWorkMinutes();

        workMinutes -= this.getLunchBreakMinutes();
        workMinutes -= this.getEveningBreakMinutes();
        workMinutes -= this.getNightBreakMinutes();

        int overWorkMinutes = this.getOverWorkMinutes(workMinutes);

        return this.getOutputString(workMinutes, overWorkMinutes);
    }

    @Override
    protected boolean isCorrectMethodType() {
        return InputData.MethodType.INPUT.equals(this.inputData.getMethodType());
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
        if(this.endTime.getHour() == breakStartHour) {
            if (this.startTime.getHour() < breakStartHour) return this.endTime.getMinute();
            if (this.startTime.getHour() == breakStartHour) return this.endTime.getMinute() - this.startTime.getMinute();
        } else if(this.endTime.getHour() > breakStartHour) {
            return 60;
        }
        return 0;
    }

    private String getOutputString(int workMinutes, int overWorkMinutes) {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                this.inputData.getDate(), this.inputData.getStartTime(), this.inputData.getEndTime(),
                workMinutes, overWorkMinutes, this.inputData.getNow()
        );
    }

    private boolean isStartLessOrEqualEnd() {
        int startMinutes = this.startTime.convertTimeToMinutes();
        int endMinutes = this.endTime.convertTimeToMinutes();

        return startMinutes <= endMinutes;
    }

    private void setFields() {
        this.startTime = new TimeData(
                Integer.valueOf(this.inputData.getStartTime().substring(0, 2)),
                Integer.valueOf(this.inputData.getStartTime().substring(2, 4))
        );

        this.endTime = new TimeData(
                Integer.valueOf(this.inputData.getEndTime().substring(0, 2)),
                Integer.valueOf(this.inputData.getEndTime().substring(2, 4))
        );

        if (!this.isStartLessOrEqualEnd())
            throw new RuntimeException("開始時刻は終了時刻より前の時刻を設定してください");
    }

}

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
        if (InputData.MethodType.INPUT.equals(inputData.getMethodType()))
            this.setFieldsWhenInputProcess(inputData);
    }

    public String getOutputData() {
        int workMinutes = this.endTime.getHour() * 60 + this.endTime.getMinute()
                - (this.startTime.getHour() * 60 + this.startTime.getMinute());

        if(this.endTime.getHour() == 12) {
            workMinutes -= this.endTime.getMinute();
        } else if(this.endTime.getHour() >= 13) {
            workMinutes -= 60;
        }

        if(this.endTime.getHour() == 18) {
            workMinutes -= this.endTime.getMinute();
        } else if(this.endTime.getHour() >= 19) {
            workMinutes -= 60;
        }

        if(this.endTime.getHour() == 21) {
            workMinutes -= this.endTime.getMinute();
        } else if(this.endTime.getHour() >= 22) {
            workMinutes -= 60;
        }

        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);

        return this.getOutputString(workMinutes, overWorkMinutes);
    }

    private String getOutputString(int workMinutes, int overWorkMinutes) {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                this.inputData.getDate(), this.inputData.getStartTime(), this.inputData.getEndTime(),
                workMinutes, overWorkMinutes, this.inputData.getNow()
        );
    }

    private void setFieldsWhenInputProcess(InputData inputData) {
        this.startTime = new TimeData(
                Integer.valueOf(inputData.getStartTime().substring(0, 2)),
                Integer.valueOf(inputData.getStartTime().substring(2, 4))
        );

        this.endTime = new TimeData(
                Integer.valueOf(inputData.getEndTime().substring(0, 2)),
                Integer.valueOf(inputData.getEndTime().substring(2, 4))
        );
    }

}

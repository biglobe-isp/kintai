package refoctor.domain;

import refoctor.domain.japan.DateDomain;
import refoctor.domain.japan.EndTime;
import refoctor.domain.japan.StartTime;
import refoctor.domain.japan.WorkMinutesInterface;

public class WorkMinutesBiglobe implements WorkMinutesInterface {

    private final int workTime;
    private final int overWork;

    public WorkMinutesBiglobe(StartTime startTime, EndTime endTime, DateDomain dateDomain){
        int totalWorkMinutes = endTime.getEndTotalMinutes() - startTime.getStartTotalMinutes();

        if (endTime.getEndHour().getValue() == 12) {
            totalWorkMinutes -= endTime.getEndMinutes().getValue();
        } else if (endTime.getEndHour().getValue() >= 13) {
            totalWorkMinutes -= 60;
        }
        if (dateDomain.isAfter20181115()) {
            if(endTime.getEndHour().getValue() == 15) {
                totalWorkMinutes -= endTime.getEndMinutes().getValue();
            } else if (endTime.getEndHour().getValue() >= 16){
                totalWorkMinutes -= 60;
            }
        }

        if (endTime.getEndHour().getValue() == 18) {
            totalWorkMinutes -= endTime.getEndMinutes().getValue();
        } else if (endTime.getEndHour().getValue() >= 19) {
            totalWorkMinutes -= 60;
        }

        if (endTime.getEndHour().getValue() == 21) {
            totalWorkMinutes -= endTime.getEndMinutes().getValue();
        } else if (endTime.getEndHour().getValue() >= 22) {
            totalWorkMinutes -= 60;
        }

        this.workTime = totalWorkMinutes;

        this.overWork = Math.max(this.workTime - 8 * 60, 0);
    }

    public int getWorkTimeValue() {
        return workTime;
    }

    public int getOverWorkTimeValue() {
        return overWork;
    }

}

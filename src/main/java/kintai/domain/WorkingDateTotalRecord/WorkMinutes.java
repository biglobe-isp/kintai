package kintai.domain.WorkingDateTotalRecord;

import kintai.domain.InputAttendance.WorkEnd;
import kintai.domain.InputAttendance.WorkStart;
import lombok.Value;

import java.time.Duration;

@Value
public class WorkMinutes {
    int workMinutes;

    public WorkMinutes(int workMinutes){
        if(workMinutes < 0){
            throw  new IllegalArgumentException("workMinutesは０以上");
        }
        this.workMinutes = workMinutes;
    }
    public static WorkMinutes calcWorkMinutes(WorkStart workStart, WorkEnd workEnd, int restTimeMinutes){
        var workM = Duration.between(workStart.getLocalTime(),workEnd.getLocalTime()).toMinutes() - restTimeMinutes;
        return new WorkMinutes((int) workM);
    }


}

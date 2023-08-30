package kintai.domain.InputAttendance;

import lombok.Value;

@Value
public class InputAttendance {
     WorkDay workDay;
     WorkStart workStart;
     WorkEnd workEnd;
    public InputAttendance(WorkDay workDay, WorkStart workStart, WorkEnd workEnd){
        this.workDay   = workDay;
        this.workStart = workStart;
        this.workEnd   = workEnd;
    }


}

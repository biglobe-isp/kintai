package kintai.domain;

import lombok.Value;

@Value
public class InputAttendance {
     WorkDay   workDay;
     StartWork startWork;
     EndWork   endWork;
    public InputAttendance(WorkDay workDay,StartWork startWork,EndWork endWork){
        this.workDay   = workDay;
        this.startWork = startWork;
        this.endWork   = endWork;
    }

}

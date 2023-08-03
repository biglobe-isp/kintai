package kintai.domain;

import lombok.Value;

@Value
public class WorkingDateTotalRecord {
    WorkDay   workDay;
    WorkStart workStart;
    WorkEnd workEnd;
    OverWorkMinutes overWorkMinutes ;
    WorkMinutes workMinutes;

    //勤務記録と就業規則を受け取って？？？？合計を記録する。
    public static WorkingDateTotalRecord fromInputAttendance(InputAttendance attendance, LaborRegulations laborRegulations){
        int startH = Integer.parseInt(attendance.getWorkStart().getValue().substring(0,2));
        int startM = Integer.parseInt(attendance.getWorkStart().getValue().substring(3,5));
        int endH   = Integer.parseInt(attendance.getWorkEnd().getValue().substring(0,2));
        int endM   = Integer.parseInt(attendance.getWorkEnd().getValue().substring(3,5));

        int restTimeMinutes = laborRegulations.calcRestTimeMinutes(startH,startM,endH,endM);
        int workMinutes = endH * 60 + endM  -(startH * 60 + startM) - restTimeMinutes;
        int overWorkMinutes = workMinutes - 8* 60;

        return new WorkingDateTotalRecord(
                attendance.getWorkDay(),
                attendance.getWorkStart(),
                attendance.getWorkEnd(),
                new OverWorkMinutes(overWorkMinutes),
                new WorkMinutes(workMinutes)
        );
    }
}

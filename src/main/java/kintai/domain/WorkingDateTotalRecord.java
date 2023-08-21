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
        int startH = attendance.getWorkStart().getLocalTime().getHour();
        int startM = attendance.getWorkStart().getLocalTime().getMinute();
        int endH   = attendance.getWorkEnd().getLocalTime().getHour();
        int endM   = attendance.getWorkEnd().getLocalTime().getMinute();

        int restTimeMinutes = laborRegulations.calcRestTimeMinutes(startH,startM,endH,endM);
        int workMinutes = WorkMinutes.calcWorkMinutes(startH,startM,endH,endM,restTimeMinutes);
        int overWorkMinutes = OverWorkMinutes.calcOverWorkMinutes(workMinutes);

        return new WorkingDateTotalRecord(
                attendance.getWorkDay(),
                attendance.getWorkStart(),
                attendance.getWorkEnd(),
                new OverWorkMinutes(overWorkMinutes),
                new WorkMinutes(workMinutes)
        );
    }
}

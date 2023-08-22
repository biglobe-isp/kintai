package kintai.domain;

import lombok.Value;

@Value
public class WorkingDateTotalRecord {
    WorkDay   workDay;
    WorkStart workStart;
    WorkEnd workEnd;
    OverWorkMinutes overWorkMinutes ;
    WorkMinutes workMinutes;
    //勤務記録と就業規則を受け取って勤務実績累計を返す。
    public static WorkingDateTotalRecord fromInputAttendance(InputAttendance attendance, LaborRegulations laborRegulations){

        int restTimeMinutes = laborRegulations.calcRestTimeMinutes(attendance.getWorkStart(),attendance.getWorkEnd());
        var workMinutes = WorkMinutes.calcWorkMinutes(attendance.getWorkStart(),attendance.getWorkEnd(),restTimeMinutes);
        var overWorkMinutes = OverWorkMinutes.calcOverWorkMinutes(workMinutes);

        return new WorkingDateTotalRecord(
                attendance.getWorkDay(),
                attendance.getWorkStart(),
                attendance.getWorkEnd(),
                overWorkMinutes,
                workMinutes
        );
    }
}

package kintai.domain.WorkingDateTotalRecord;

import kintai.domain.InputAttendance.InputAttendance;
import kintai.domain.InputAttendance.WorkDay;
import kintai.domain.InputAttendance.WorkEnd;
import kintai.domain.InputAttendance.WorkStart;
import kintai.domain.LaborRegulations.LaborRegulations;
import lombok.Value;

@Value
public class WorkingDateTotalRecord {
    WorkDay workDay;
    WorkStart workStart;
    WorkEnd workEnd;
    WorkMinutes workMinutes;
    OverWorkMinutes overWorkMinutes ;
    //勤務記録と就業規則を受け取って勤務実績累計を返す。

    public static WorkingDateTotalRecord fromInputAttendance(InputAttendance attendance, LaborRegulations laborRegulations){

        int restTimeDuration = laborRegulations.calcrestTimeDuration(attendance.getWorkStart(),attendance.getWorkEnd());
        var workMinutes  = WorkMinutes.calcWorkMinutes(attendance.getWorkStart(),attendance.getWorkEnd(),restTimeDuration);
        var overWorkMinutes = OverWorkMinutes.calcOverWorkMinutes(workMinutes.getWorkMinutes());

        return new WorkingDateTotalRecord(
                attendance.getWorkDay(),
                attendance.getWorkStart(),
                attendance.getWorkEnd(),
                workMinutes,
                overWorkMinutes
        );
    }
}

package kintai.datasource

import kintai.domain.WorkingDateTotalRecord.OverWorkMinutes
import kintai.domain.InputAttendance.WorkDay
import kintai.domain.InputAttendance.WorkEnd
import kintai.domain.WorkingDateTotalRecord.WorkMinutes
import kintai.domain.InputAttendance.WorkStart
import kintai.domain.WorkingDateTotalRecord.WorkingDateTotalRecord

class FixtureCsvWorkingTotal {
    static List<WorkingDateTotalRecord> get(){
        List.of(
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-16"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60))),
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-17"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60))),
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-19"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60)))
        )
    }


}

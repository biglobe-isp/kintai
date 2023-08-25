package kintai.datasource

import kintai.domain.OverWorkMinutes
import kintai.domain.WorkDay
import kintai.domain.WorkEnd
import kintai.domain.WorkMinutes
import kintai.domain.WorkStart
import kintai.domain.WorkingDateTotalRecord

class FixtureCsvWorkingTotal {
    static List<WorkingDateTotalRecord> get(){
        List.of(
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-16"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60))),
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-17"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60))),
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-19"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60)))
        )
    }


}

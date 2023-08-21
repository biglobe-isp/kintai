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
                (new WorkingDateTotalRecord(new WorkDay("2023-08-17"),WorkStart.parse("9:00:00"),new WorkEnd("20:00:00"),new OverWorkMinutes(60),new WorkMinutes(540))),
                (new WorkingDateTotalRecord(new WorkDay("2023-08-18"),WorkStart.parse("9:00:00"),new WorkEnd("20:00:00"),new OverWorkMinutes(60),new WorkMinutes(540))),
                (new WorkingDateTotalRecord(new WorkDay("2023-08-19"),WorkStart.parse("9:00:00"),new WorkEnd("20:00:00"),new OverWorkMinutes(60),new WorkMinutes(540)))
        )
    }


}

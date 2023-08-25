package kintai.datasource

import kintai.domain.OverWorkMinutes
import kintai.domain.WorkDay
import kintai.domain.WorkEnd
import kintai.domain.WorkMinutes
import kintai.domain.WorkStart
import kintai.domain.WorkingDateTotalRecord;

import java.util.List;

public class FixtureWorkingDateTotalRecord {
    static WorkingDateTotalRecord get(){
        new WorkingDateTotalRecord(WorkDay.parse("2023-08-16"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60),)

    }
}

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
        new WorkingDateTotalRecord(new WorkDay("2023-08-16"),WorkStart.parse("09:00:00"),new WorkEnd("20:00:00"),new OverWorkMinutes(60),new WorkMinutes(540))

    }
}

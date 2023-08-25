package kintai.domain

class FixtureWorkingDateTotalRecordList {
    static List<WorkingDateTotalRecord> get(){
        List.of(
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-01"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new OverWorkMinutes(60),new WorkMinutes(540))),
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-02"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:30:00"),new OverWorkMinutes(90),new WorkMinutes(570))),
                (new WorkingDateTotalRecord(WorkDay.parse("2023-08-03"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:40:00"),new OverWorkMinutes(100),new WorkMinutes(580)))
        )
    }
}
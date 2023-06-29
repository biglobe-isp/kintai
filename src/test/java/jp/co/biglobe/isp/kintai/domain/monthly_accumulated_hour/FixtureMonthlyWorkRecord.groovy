package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour

class FixtureMonthlyWorkRecord {
    static MonthlyWorkRecord get() {
        new MonthlyWorkRecord(FixtureWorkRecordList.get())
    }
}

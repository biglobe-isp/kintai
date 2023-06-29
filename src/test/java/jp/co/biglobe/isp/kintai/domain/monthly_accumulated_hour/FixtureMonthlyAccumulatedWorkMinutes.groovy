package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour

class FixtureMonthlyAccumulatedWorkMinutes {
    static MonthlyAccumulatedWorkMinutes get() {
        MonthlyAccumulatedWorkMinutes.from(FixtureMonthlyWorkRecord.get().getWorkRecordList())
    }

    static AccumulatedWorkRecordMinutesFromTestCsv() {
        1500
    }

    static AccumulatedOverTimeMinutesFromTestCsv() {
        60
    }
}

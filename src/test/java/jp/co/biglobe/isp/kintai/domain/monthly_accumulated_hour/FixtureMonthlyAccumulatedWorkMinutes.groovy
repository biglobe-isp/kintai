package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour

class FixtureMonthlyAccumulatedWorkMinutes {
    static MonthlyAccumulatedWorkMinutes get() {
        MonthlyAccumulatedWorkMinutes.from(FixtureMonthlyWorkRecord.get())
    }
}

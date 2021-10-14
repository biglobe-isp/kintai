package kintai.domain

class FixtureWorkDuration {
    static WorkDuration getFullTime() {
        new WorkDuration(FixtureAttendanceTime.getEndBetweenNightBreak())
    }

    static WorkDuration getShortTime() {
        new WorkDuration(FixtureAttendanceTime.getEndBeforeLunchBreak())
    }

    static WorkDuration getOverTime() {
        new WorkDuration(FixtureAttendanceTime.getEndAfterMidNightBreak())
    }

}

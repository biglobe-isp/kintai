package kintai.domain

class FixtureAttendance {

    static Attendance getAttendance1() {

        new Attendance(
                FixtureAttendanceDate.getAttendanceDate1(),
                FixtureAttendanceTime.getEndBetweenNightBreak(),
                FixtureWorkDuration.getFullTime(),
                FixtureOverWorkDuration.get())
    }

    static Attendance getAttendance2() {

        new Attendance(
                FixtureAttendanceDate.getAttendanceDate2(),
                FixtureAttendanceTime.getEndAfterMidNightBreak(),
                FixtureWorkDuration.getOverTime(),
                FixtureOverWorkDuration.get())
    }

    static Attendance getAttendance3() {

        new Attendance(
                FixtureAttendanceDate.getAttendanceDate3(),
                FixtureAttendanceTime.getEndBeforeLunchBreak(),
                FixtureWorkDuration.getShortTime(),
                FixtureOverWorkDuration.get())
    }

    static Attendance getAttendance4() {

        new Attendance(
                FixtureAttendanceDate.getAttendanceDate1(),
                FixtureAttendanceTime.getEndBetweenNightBreak(),
                FixtureWorkDuration.getFullTime(),
                FixtureOverWorkDuration.get())
    }

}

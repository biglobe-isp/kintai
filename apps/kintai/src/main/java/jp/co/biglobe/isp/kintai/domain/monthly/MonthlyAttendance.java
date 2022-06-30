package jp.co.biglobe.isp.kintai.domain.monthly;

public record MonthlyAttendance(AttendanceYearMonth yearMonth, DailyAttendancesOfMonth dailyAttendancesOfMonth) {
    public TotalWorkedHoursResult totalWorkingHours() {
        return dailyAttendancesOfMonth.totalWorkedHours();
    }
}

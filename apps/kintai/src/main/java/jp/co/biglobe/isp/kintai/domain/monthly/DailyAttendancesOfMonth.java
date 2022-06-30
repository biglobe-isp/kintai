package jp.co.biglobe.isp.kintai.domain.monthly;

import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.daily.WorkTimeMinutes;

import java.util.List;

public record DailyAttendancesOfMonth(List<DailyAttendance> attendances) {
    public TotalWorkedHoursResult totalWorkedHours() {
        final int totalWorkTimeMinutes = attendances.stream().map(DailyAttendance::workTimeMinutes).mapToInt(
                WorkTimeMinutes::value).sum();
        final int totalOvertimeMinutes = attendances.stream().map(DailyAttendance::overtimeMinutes)
                .mapToInt(OvertimeMinutes::value).sum();
        final TotalWorkedHoursResult result = new TotalWorkedHoursResult(
                new TotalWorkTimeMinutes(totalWorkTimeMinutes),
                new TotalOvertimeMinutes(totalOvertimeMinutes)
        );
        return result;
    }
}

package jp.co.biglobe.isp.kintai.domain.monthly;

import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;

import java.util.List;

public record DailyAttendancesOfMonth(List<DailyAttendance> attendances) {
    public TotalWorkedHoursResult totalWorkedHours() {
        return null;
        //return TotalWorkedHoursResult.create(null);
    }
}

package jp.co.biglobe.isp.kintai.domain;

import jp.co.biglobe.isp.kintai.domain.entity.DailyAttendance;

import java.util.List;

public record DailyAttendancesOfMonth(List<DailyAttendance> attendances) {
    public TotalWorkedHoursResult totalWorkedHours() {
        return null;
        //return TotalWorkedHoursResult.create(null);
    }
}

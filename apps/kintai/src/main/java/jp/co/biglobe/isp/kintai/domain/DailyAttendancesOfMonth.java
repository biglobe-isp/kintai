package apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain;

import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.entity.DailyAttendance;

import java.util.List;

public record DailyAttendancesOfMonth(List<DailyAttendance> attendances) {
    // TODO 日次勤怠-> (労働時間,残業時間) -> 加算
/*
    public TotalWorkedHoursResult totalWorkedHours() {

        attendances.stream().map(attendance -> )
        return TotalWorkedHoursResult.create(this);
    }
*/
}

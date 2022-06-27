package jp.co.biglobe.isp.kintai.domain.monthly;

import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.daily.WorkTimeMinutes;

public record TotalWorkedHoursResult(WorkTimeMinutes workTimeHours, OvertimeMinutes overtimeHours) {
}

package jp.co.esumit.kintai.domain.summary;

import jp.co.esumit.kintai.domain.summary.total_office_minutes.TotalOfficeMinutes;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutes;
import lombok.Value;

@Value
public class MonthlySummary {
    TotalOfficeMinutes totalOfficeMinutes;
    TotalOvertimeMinutes totalOvertimeMinutes;

    public int getTotalOfficeMinsValue() {
        return this.totalOfficeMinutes.getValue();
    }

    public int getTotalOvertimeValue() {
        return this.totalOvertimeMinutes.getValue();
    }
}

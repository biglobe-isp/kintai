package jp.co.esumit.kintai.domain.kintai_info;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import lombok.Value;

@Value
public class KintaiInfo {
    TargetDay targetDay;
    StartTime startTime;
    EndTime endTime;
    OfficeMinutes officeMinutes;
    OvertimeMinutes overtimeMinutes;
    RegisteredTime registeredTime;

    public int getOfficeMinutesValue() {
        return this.officeMinutes.getValue();
    }

    public int getOvertimeValue() {
        return this.overtimeMinutes.getValue();
    }
}

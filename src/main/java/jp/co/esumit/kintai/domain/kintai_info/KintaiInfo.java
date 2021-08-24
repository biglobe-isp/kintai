package jp.co.esumit.kintai.domain.kintai_info;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes.ActualWorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes.OperatingMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class KintaiInfo {
    private final TargetDay targetDay;
    private final StartTime startTime;
    private final EndTime endTime;
    private final ActualWorkingMinutes actualWorkingMinutes;
    private final OvertimeMinutes overtimeMinutes;
    private final RegisteredTime registeredTime;

    public static KintaiInfo create(TargetDay targetDay, StartTime startTime, EndTime endTime) {

        ActualWorkingMinutes actualWorkingMinutes = ActualWorkingMinutes.create(
                OperatingMinutes.create(startTime, endTime), endTime);
        OvertimeMinutes overtimeMinutes = OvertimeMinutes.create(actualWorkingMinutes);
        RegisteredTime registeredTime = RegisteredTime.create();

        return new KintaiInfo(
                targetDay,
                startTime,
                endTime,
                actualWorkingMinutes,
                overtimeMinutes,
                registeredTime
        );
    }

    public int getActualWorkingMinutesValue() {

        return this.actualWorkingMinutes.getValue();
    }

    public int getOvertimeValue() {

        return this.overtimeMinutes.getValue();
    }
}

package jp.co.esumit.kintai.domain.kintai_record.working_minutes;

import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.ActualWorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.BreakTimes;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.FixedTime;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.OvertimeMinutes;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class WorkingMinutes {
    ActualWorkingMinutes actualWorkingMinutes;
    OvertimeMinutes overtimeMinutes;

    public WorkingMinutes(TimeCard stampingTimes, BreakTimes breakTimes, FixedTime fixedTime) {
        actualWorkingMinutes = ActualWorkingMinutes.create(stampingTimes, breakTimes);
        overtimeMinutes = OvertimeMinutes.create(actualWorkingMinutes, fixedTime);
    }
}

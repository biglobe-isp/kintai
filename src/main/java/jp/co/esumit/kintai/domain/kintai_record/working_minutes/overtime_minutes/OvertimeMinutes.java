package jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes;

import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.ActualWorkingMinutes;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class OvertimeMinutes {
    int value;

    public static OvertimeMinutes create(ActualWorkingMinutes actualWorkingMinutes, FixedTime fixedTime) {
        return new OvertimeMinutes(Math.max(0, actualWorkingMinutes.getValue() - fixedTime.getMinutes()));
    }
}


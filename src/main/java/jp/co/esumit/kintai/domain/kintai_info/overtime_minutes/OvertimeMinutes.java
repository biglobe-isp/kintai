package jp.co.esumit.kintai.domain.kintai_info.overtime_minutes;

import jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes.ActualWorkingMinutes;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class OvertimeMinutes {
    int value;

    public static OvertimeMinutes create(ActualWorkingMinutes actualWorkingMinutes) {
        return new OvertimeMinutes(Math.max(0, actualWorkingMinutes.getValue() - 8 * 60));
    }
}


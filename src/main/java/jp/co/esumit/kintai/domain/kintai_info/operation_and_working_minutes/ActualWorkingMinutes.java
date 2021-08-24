package jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class ActualWorkingMinutes {
    int value;

    public static ActualWorkingMinutes create(OperatingMinutes operatingMinutes, EndTime endTime) {
        return new ActualWorkingMinutes(operatingMinutes.getValue() - calcTotalBreakMinutes(endTime));
    }

    private static int calcTotalBreakMinutes(EndTime endTime) {

        // 休憩時間
        final int[][] breakTimes = {{12, 13}, {15, 16}, {18, 19}, {21, 22}};

        int breakMinute = 0;

        for (int[] breakTime : breakTimes) {
            if (endTime.getEndHrValue() == breakTime[0]) {
                breakMinute += endTime.getEndMinValue();
            } else if (endTime.getEndHrValue() >= breakTime[1]) {
                breakMinute += 60;
            }
        }

        return breakMinute;
    }
}

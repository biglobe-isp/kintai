package jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.val;

import java.time.LocalTime;

/**
 * 勤務時間
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OperatingMinutes {
    private final int value;

    public static OperatingMinutes create(StartTime startTime, EndTime endTime) {

        valid(startTime, endTime);
        return new OperatingMinutes(endTime.getMinutes() - startTime.getMinutes());
    }

    //　NOTE:　getValueで計算するのもあり　→
    //　ValueObjectをもたせる。という案もある

    private static void valid(StartTime startTime, EndTime endTime) {

        val start = LocalTime.of(startTime.getStartHrValue(), startTime.getStartMinValue(), 0);
        val end = LocalTime.of(endTime.getEndHrValue(), endTime.getEndMinValue(), 0);

        if (start.isAfter(end)) {
            throw new RuntimeException("終了時刻は開始時刻より後に設定してください。");
        }
    }
}

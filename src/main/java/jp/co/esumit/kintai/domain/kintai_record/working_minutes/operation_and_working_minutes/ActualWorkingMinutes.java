package jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes;

import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 実働時間
 */
@Value
@RequiredArgsConstructor
public class ActualWorkingMinutes {
    int value;

    public static ActualWorkingMinutes create(TimeCard timeCard, BreakTimes breakTimes) {
        OperatingMinutes operatingMinutes = OperatingMinutes.create(timeCard);
        return new ActualWorkingMinutes(operatingMinutes.getValue() - calcTotalBreakMinutes(timeCard, breakTimes));
    }

    private static int calcTotalBreakMinutes(TimeCard timeCard, BreakTimes breakTimes) {

        return breakTimes.getBreakTimes().stream().mapToInt(breakTime -> {

            // 休憩時間に作業時間が被っていない => 0分　を休憩時間とする。
            if (timeCard.getEndTime().getMinutes() <= breakTime.getStartMinutes()
                    || breakTime.getEndMinutes() <= timeCard.getStartTime().getMinutes())
                return 0;

            //休憩時間中に作業開始/終了した場合　=> 作業時間をすべて休憩時間とする。
            if (breakTime.getStartMinutes() <= timeCard.getStartTime().getMinutes()
                    && breakTime.getEndMinutes() >= timeCard.getEndTime().getMinutes())
                return timeCard.getEndTime().getMinutes() - timeCard.getStartTime().getMinutes();

            // 休憩時間中に作業開始　=> 作業開始〜休憩終了時間　を休憩時間とする。
            if (breakTime.getStartMinutes() <= timeCard.getStartTime().getMinutes()
                    && breakTime.getEndMinutes() >= timeCard.getStartTime().getMinutes())
                return breakTime.getEndMinutes() - timeCard.getStartTime().getMinutes();

            // 休憩時間中に作業が終了 => 休憩開始〜作業終了　を休憩時間とする。
            if (timeCard.getStartTime().getMinutes() <= breakTime.getStartMinutes()
                    && timeCard.getEndTime().getMinutes() <= breakTime.getEndMinutes())
                return timeCard.getEndTime().getMinutes() - breakTime.getStartMinutes();

            //　その他の場合 => 休憩時間は　休憩開始〜休憩終了まるまる。
            return breakTime.getEndMinutes() - breakTime.getStartMinutes();
        }).reduce(0, Integer::sum);
    }
}

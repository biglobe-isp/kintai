package domain;

import japan.EndTime;
import japan.Japan;
import japan.StartTime;

/**
 * 労働時間（分）のValueObjectクラス。
 */
public class WorkTimeMinuteVO {

    private final int value;

    public WorkTimeMinuteVO(StartTime startTime, EndTime endTime) {

        Japan japan = new Japan();
        RestTimeCalculator restTimeCalculator = new RestTimeCalculator();

        //勤務時間
        int workMinute = japan.getWorkTimeMinute(startTime, endTime);//TODO 変数名改善の余地あり。

        //休憩時間
        int totalRestMinute = restTimeCalculator.calcTotalRestTime(endTime);

        //労働時間＝勤務時間ー休憩時間
        this.value = workMinute - totalRestMinute;

    }

    public int getValue() {
        return this.value;
    }
}
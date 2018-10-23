package domain.japan;

import domain.RestTimeCalculator;

/**
 * 労働時間（分）のValueObjectクラス。
 */
public class WorkTimeMinuteVO {

    private final int value;

    public WorkTimeMinuteVO(StartTime startTime, EndTime endTime) {

        RestTimeCaluculatorRepository restTimeCalculatorRepository = new RestTimeCalculator();

        //勤務時間＝終業時間ー始業時間
        int workMinute = endTime.getEndTimeHour() * 60 + endTime.getEndTimeMinute()
                        - (startTime.getStartTimeHour() * 60 + startTime.getStartTimeMinute());

        //休憩時間
        int totalRestMinute = restTimeCalculatorRepository.calcTotalRestTime(endTime);

        //労働時間＝勤務時間ー休憩時間
        this.value = workMinute - totalRestMinute;

    }

    public int getValue() {
        return this.value;
    }
}
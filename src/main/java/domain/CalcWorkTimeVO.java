package domain;

import domain.Japan.EndTimeVO;
import domain.Japan.ICalcWorkTimeVO;
import domain.Japan.StartTimeVO;

/*
 * 勤務時間・残業時間を計算する実装クラス
 */
public class CalcWorkTimeVO implements ICalcWorkTimeVO {
    private final int workTime;
    private final int overWorkTime;
    private int tmpBreakTime;

    public CalcWorkTimeVO(StartTimeVO startTime, EndTimeVO endTime) {
        //休憩時間の計算
        if (endTime.getHour() == 12) {
            tmpBreakTime -= endTime.getMinutes();
        } else if (endTime.getHour() >= 13) {
            tmpBreakTime -= 60;
        }

        if (endTime.getHour() == 18) {
            tmpBreakTime -= endTime.getMinutes();
        } else if (endTime.getHour() >= 19) {
            tmpBreakTime -= 60;
        }

        if (endTime.getHour() == 21) {
            tmpBreakTime -= endTime.getMinutes();
        } else if (endTime.getHour() >= 22) {
            tmpBreakTime -= 60;
        }

        //就業時間＝終業時間ー始業時間＋休憩時間（休憩時間にはマイナスの値が入っている）
        //-180<= tmpBreakTime && tmpBreakTime <= 0
        this.workTime = endTime.getTotalMinutes() - startTime.getTotalMinutes()
                + tmpBreakTime;

        //残業時間＝就業時間ー480分(8時間)
        this.overWorkTime = Math.max(this.workTime - 8 * 60, 0); //TODO 時刻を分にする*60などは厳密にはDomainではない。
    }

    //就業時間
    public int getWorkTime() {
        return workTime;
    }

    //残業時間
    public int getOverWorkTime() {
        //return Math.max(this.workTime - 8 * 60, 0);
        return overWorkTime;
    }

}

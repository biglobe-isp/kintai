package domain;

public class WorkTimeVO {
    private final int workTime;
    private final int overWorkTime;
    private int tmpBreakTime;

    public WorkTimeVO(StartTimeVO startVO, EndTimeVO endVO) {

        //休憩時間の計算
        if (endVO.getEndHour() == 12) {
            tmpBreakTime -= endVO.getEndMinute();
        } else if (endVO.getEndHour() >= 13) {
            tmpBreakTime -= 60;
        }

        if (endVO.getEndHour() == 18) {
            tmpBreakTime -= endVO.getEndMinute();
        } else if (endVO.getEndHour() >= 19) {
            tmpBreakTime -= 60;
        }

        if (endVO.getEndHour() == 21) {
            tmpBreakTime -= endVO.getEndMinute();
        } else if (endVO.getEndHour() >= 22) {
            tmpBreakTime -= 60;
        }

        //就業時間＝終業時間ー始業時間＋休憩時間（休憩時間にはマイナスの値が入っている）
        this.workTime = endVO.getEndTotalMinute() - startVO.getStartTotalMinute()
                + tmpBreakTime; //TODO 変数名でTimeとMinuteが混合している

        //残業時間＝就業時間ー480分(8時間)
        this.overWorkTime = Math.max(this.workTime - 8 * 60, 0);
    }

    //就業時間
    public int getWorkTime() {
        //TODO 休憩時間の計算ロジックはどこで記述するか
        return workTime;
    }

    //残業時間
    public int getOverWorTime() {
        //return Math.max(this.workTime - 8 * 60, 0);
        return overWorkTime;
    }


}

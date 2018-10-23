package japan;

import domain.EndTimeHourVO;
import domain.EndTimeMinuteVO;

/**
 * 退勤時間（HHMM）のValueObjectクラス。
 */
public class EndTime {

    private EndTimeHourVO endTimeHourVO;
    private EndTimeMinuteVO endTimeMinuteVO;

    public EndTime(String endTime) {
        this.endTimeHourVO = new EndTimeHourVO(endTime);
        this.endTimeMinuteVO =  new EndTimeMinuteVO(endTime);
    }

    public int getEndTimeHour() {
        return this.endTimeHourVO.getValue();
    }

    public int getEndTimeMinute() {
        return this.endTimeMinuteVO.getValue();
    }

}
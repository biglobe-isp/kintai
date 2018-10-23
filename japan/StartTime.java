package japan;

import domain.StartTimeHourVO;
import domain.StartTimeMinuteVO;

/**
 * 出勤時間（HHMM）のValueObjectクラス。
 */
public class StartTime {

    private StartTimeHourVO startTimeHourVO;
    private StartTimeMinuteVO startTimeMinuteVO;

    public StartTime(String startTime) {
        this.startTimeHourVO = new StartTimeHourVO(startTime);
        this.startTimeMinuteVO =  new StartTimeMinuteVO(startTime);
    }

    public int getStartTimeHour() {
        return this.startTimeHourVO.getValue();
    }

    public int getStartTimeMinute() {
        return this.startTimeMinuteVO.getValue();
    }

}
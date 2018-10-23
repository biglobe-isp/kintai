package japan;

import domain.WorkTimeMinuteVO;

public class Japan {

    public int getWorkTimeMinute(StartTime startTime, EndTime endTime){
        //勤務時間＝終業時間ー始業時間
        int dayWorkMinute = endTime.getEndTimeHour() * 60 + endTime.getEndTimeMinute()
                            - (startTime.getStartTimeHour() * 60 + startTime.getStartTimeMinute());

        return dayWorkMinute;
    }

    public int getOverWorkTimeMinute(WorkTimeMinuteVO workTimeMinuteVO){
        return Math.max(workTimeMinuteVO.getValue() - 8 * 60, 0);
    }
}

package jp.co.biglobe.isp.kintai.domain.attendance_record;

import jp.co.biglobe.isp.kintai.domain.work_regulation.BreakTime;
import jp.co.biglobe.isp.kintai.domain.work_regulation.WorkRegulation;
import lombok.Value;

/**
 * 実働時間
 */
@Value
public class WorkRecordMinutes {
    public static int from(WorkTime workTime, WorkRegulation workRegulation) {
        var workTimeMinutes = workTime.getWorkTimeMinutes();
        var breakTimeList = workRegulation.getBreakTimeList();

        var closingHours = workTime.getClosingTime().getHour();
        var closingMinutes = workTime.getClosingTime().getMinute();
        var restMinutes = 0;

        for(BreakTime breakTime: breakTimeList) {
            var breakStartHours = breakTime.getBreakStartTime().getHour();
            var breakEndHours = breakTime.getBreakEndTime().getHour();
            if(closingHours == breakStartHours) restMinutes += closingMinutes;
            else if (closingHours >= breakEndHours) restMinutes += 60;
        }

        return workTimeMinutes - restMinutes;
    }

}

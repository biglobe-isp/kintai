package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkBeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkEndTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;
import lombok.Getter;
import lombok.ToString;


/**
 * 日別拘束時間
 * <pre>
 *     実際に拘束された時間。
 * </pre>
 */
@ToString
@Getter
public class DailySpentTimeRangeAtWork {

    final AttendanceDate attendanceDate;
    final WorkTimeRange spentTimeRange;


    /* 生成 */

    private DailySpentTimeRangeAtWork(AttendanceDate attendanceDate, WorkBeginTime workBeganTime, WorkEndTime workEndedTime) {

        this.attendanceDate = attendanceDate;
        spentTimeRange = WorkTimeRange.of(workBeganTime, workEndedTime);
    }

    public static DailySpentTimeRangeAtWork of(AttendanceDate attendanceDate, WorkBeginTime workBeganTime, WorkEndTime workEndedTime) {
        return new DailySpentTimeRangeAtWork(attendanceDate, workBeganTime, workEndedTime);
    }


    /* 導出 */

    public DailyWorkedTime calculateDetailedWorkTime() {

        DailyWorkedTime dailyWorkedTime = DailyWorkedTime.of(this);
        return dailyWorkedTime;
    }
}

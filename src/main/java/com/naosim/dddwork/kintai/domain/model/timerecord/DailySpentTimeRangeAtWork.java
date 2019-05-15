package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.core.type.time.range.TimeRange;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.BeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.EndTime;
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
    final TimeRange spentTimeRange;


    public static DailySpentTimeRangeAtWork of(AttendanceDate attendanceDate, BeginTime beganTime, EndTime endedTime) {
        return new DailySpentTimeRangeAtWork(attendanceDate, beganTime, endedTime);
    }

    public DailySpentTimeRangeAtWork(AttendanceDate attendanceDate, BeginTime beganTime, EndTime endedTime) {

        this.attendanceDate = attendanceDate;
        spentTimeRange = new TimeRange(beganTime, endedTime);
    }
}

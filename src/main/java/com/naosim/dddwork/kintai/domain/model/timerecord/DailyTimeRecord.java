package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkBeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkEndTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * 日別タイムレコード（Entity）
 * <pre>
 *     実際に拘束された時間を記録したもの。
 * </pre>
 */
@ToString
@Getter
public class DailyTimeRecord {

    //TODO: @ID とかにしたい
    @EqualsAndHashCode.Include
    final AttendanceDate attendanceDate;

    final WorkTimeRange spentTimeRange;


    /* 生成 */

    private DailyTimeRecord(AttendanceDate attendanceDate, WorkBeginTime workBeganTime, WorkEndTime workEndedTime) {

        this.attendanceDate = attendanceDate;
        spentTimeRange = WorkTimeRange.of(workBeganTime, workEndedTime);
    }

    public static DailyTimeRecord of(AttendanceDate attendanceDate, WorkBeginTime workBeganTime, WorkEndTime workEndedTime) {
        return new DailyTimeRecord(attendanceDate, workBeganTime, workEndedTime);
    }


    /* 導出 */

    public DailyWorkedTime calculateDetailedWorkTime() {

        DailyWorkedTime dailyWorkedTime = DailyWorkedTime.of(this);
        return dailyWorkedTime;
    }


    /* 比較 */

    public boolean isIncludedIn(AttendanceYearMonth yearMonth) {
        return attendanceDate.isIncludedIn(yearMonth);
    }
}

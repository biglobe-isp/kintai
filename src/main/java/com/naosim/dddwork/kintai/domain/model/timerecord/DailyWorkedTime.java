package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.core.type.time.stamp.RecordTimestamp;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.BeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.EndTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.PaidWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.WorkedTimeAsOvertime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.WorkedTimeAsRegular;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * 日別勤務時間
 */
@EqualsAndHashCode
@ToString
@Getter
public class DailyWorkedTime {

    final DailySpentTimeRangeAtWork dailySpentTimeRangeAtWork;
    final PaidWorkedTime paidWorkedTime;
    final RecordTimestamp recordTimestamp = RecordTimestamp.now();


    public static DailyWorkedTime of(AttendanceDate attendanceDate, BeginTime beginTime, EndTime endTime, WorkedTimeAsRegular workMinutes, WorkedTimeAsOvertime workedTimeAsOvertime) {
        return new DailyWorkedTime(attendanceDate, beginTime, endTime, workMinutes, workedTimeAsOvertime);
    }

    public DailyWorkedTime(AttendanceDate attendanceDate, BeginTime beginTime, EndTime endTime, WorkedTimeAsRegular workedTimeAsRegular, WorkedTimeAsOvertime workedTimeAsOvertime) {

        this.dailySpentTimeRangeAtWork = DailySpentTimeRangeAtWork.of(attendanceDate, beginTime, endTime);
        paidWorkedTime = PaidWorkedTime.of(workedTimeAsRegular, workedTimeAsOvertime);
    }
}

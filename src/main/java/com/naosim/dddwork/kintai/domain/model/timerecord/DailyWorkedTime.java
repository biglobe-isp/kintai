package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.stamp.RecordTimestamp;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.BeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.EndTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.WorkedTimeAsOvertime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * 日別勤務時間
 */
@EqualsAndHashCode
@ToString
@Getter
//TODO: 属性をドメインオブジェクトにしていく
public class DailyWorkedTime {

    final AttendanceDate attendanceDate;
//TODO: ここはまとめる
    final BeginTime beginTime;
    final EndTime endTime;
//TODO: ここはまとめる
    final AmountOfMinutes workMinutes;
    final WorkedTimeAsOvertime workedTimeAsOvertime;
    final RecordTimestamp recordTimestamp = RecordTimestamp.now();


    public static DailyWorkedTime of(AttendanceDate attendanceDate, BeginTime beginTime, EndTime endTime, AmountOfMinutes workMinutes, WorkedTimeAsOvertime workedTimeAsOvertime) {
        return new DailyWorkedTime(attendanceDate, beginTime, endTime, workMinutes, workedTimeAsOvertime);
    }

    public DailyWorkedTime(AttendanceDate attendanceDate, BeginTime beginTime, EndTime endTime, AmountOfMinutes workMinutes, WorkedTimeAsOvertime workedTimeAsOvertime) {

        this.attendanceDate = attendanceDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.workMinutes = workMinutes;
        this.workedTimeAsOvertime = workedTimeAsOvertime;
    }
}

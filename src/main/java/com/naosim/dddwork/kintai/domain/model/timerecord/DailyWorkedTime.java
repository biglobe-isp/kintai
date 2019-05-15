package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.BeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.EndTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


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
    final AmountOfMinutes overWorkMinutes;
    final String now = LocalDateTime.now().toString();


    public static DailyWorkedTime of(AttendanceDate attendanceDate, BeginTime beginTime, EndTime endTime, AmountOfMinutes workMinutes, AmountOfMinutes overWorkMinutes) {
        return new DailyWorkedTime(attendanceDate, beginTime, endTime, workMinutes, overWorkMinutes);
    }

    public DailyWorkedTime(AttendanceDate attendanceDate, BeginTime beginTime, EndTime endTime, AmountOfMinutes workMinutes, AmountOfMinutes overWorkMinutes) {

        this.attendanceDate = attendanceDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
    }
}

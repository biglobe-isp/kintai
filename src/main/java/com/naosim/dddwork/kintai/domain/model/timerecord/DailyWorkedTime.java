package com.naosim.dddwork.kintai.domain.model.timerecord;

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
public class DailyWorkedTime {

    final String attendanceDate;
    final String beginTime;
    final String endTime;
    final int workMinutes;
    final int overWorkMinutes;
    final String now = LocalDateTime.now().toString();


    public static DailyWorkedTime of(String attendanceDate, String beginTime, String endTime, int workMinutes, int overWorkMinutes) {
        return new DailyWorkedTime(attendanceDate, beginTime, endTime, workMinutes, overWorkMinutes);
    }

    public DailyWorkedTime(String attendanceDate, String beginTime, String endTime, int workMinutes, int overWorkMinutes) {

        this.attendanceDate = attendanceDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
    }
}

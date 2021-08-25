package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import lombok.Value;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Value
public class AttendanceDate {

    ZonedDateTime zonedDateTime;

    public AttendanceDate(String attendanceDate) throws Exception {
        if (attendanceDate == null || "".equals(attendanceDate)) {
            throw new IllegalArgumentException("引数が足りません。");
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            this.zonedDateTime = ZonedDateTime.ofInstant(dateFormat.parse(attendanceDate).toInstant(), ZoneId.systemDefault());

        } catch (Exception e) {
            throw new Exception("不正な日付です。");
        }
    }
    
}

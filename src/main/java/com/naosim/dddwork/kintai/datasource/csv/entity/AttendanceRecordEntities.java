package com.naosim.dddwork.kintai.datasource.csv.entity;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import lombok.Value;

import java.util.List;

@Value
public class AttendanceRecordEntities {
    List<AttendanceRecordEntity> records;

    public AttendanceRecordEntities(List<AttendanceRecordEntity> records) {
        if (records == null) {
            throw new IllegalArgumentException("出勤記録リストがありません。");
        }
        this.records = records;
    }

    // HACK: Immutableにする
    public void add(AttendanceRecord record) {
        AttendanceRecordEntity newRecord = new AttendanceRecordEntity(
                record.getAttendanceLocalDate(),
                record.getAttendanceStartLocalTime(),
                record.getAttendanceEndLocalTime(),
                record.getActualWorkingTimeMinutesLength(),
                record.getActualOvertimeMinutesLength()
        );
        records.add(newRecord);
    }



}

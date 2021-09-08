package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import lombok.NonNull;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class AttendanceRecords {

    @NonNull
    List<AttendanceRecord> attendanceRecords;

    public AttendanceRecords(List<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = Collections.unmodifiableList(attendanceRecords);
    }
}

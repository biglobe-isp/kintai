package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import io.vavr.collection.List;

import java.util.HashMap;

public class AttendanceRecords {
    private final List<AttendanceRecord> records;

    public AttendanceRecords() {
        records = List.empty();
    }

    public AttendanceRecords(List<AttendanceRecord> records) {
        this.records = records;
    }

    public AttendanceRecords insert(AttendanceRecord attendanceRecord) {
        // Business Requirement : override the information if update with same date key
        HashMap<Integer, AttendanceRecord> map = new HashMap<>();
        for (AttendanceRecord record : records) {
            WorkingDate workingDate = record.getWorkingDate();
            map.put(workingDate.getValue(), record);
        }
        map.put(attendanceRecord.getWorkingDate().getValue(), attendanceRecord);

        List<AttendanceRecord> newRecords = List.ofAll(map.values());
        return new AttendanceRecords(newRecords);
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return records;
    }
}

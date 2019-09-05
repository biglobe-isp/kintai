package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.rules.OverTimeRule;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AttendanceRecordUpdateService {
    public void update(AttendanceRecord attendanceRecord) {
        attendanceRecord = OverTimeRule.adjustAttendanceRecord(attendanceRecord);

        AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        AttendanceRecords attendanceRecords = repository.load().insert(attendanceRecord);
        repository.save(attendanceRecords);
    }
}

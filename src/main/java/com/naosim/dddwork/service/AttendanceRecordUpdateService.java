package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.rules.OverTimeRule;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AttendanceRecordUpdateService {
    private static OverTimeRule overTimeRule = new OverTimeRule();

    public void update(AttendanceRecord attendanceRecord) {

        AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        AttendanceRecords attendanceRecords = repository.load()
                .insert(overTimeRule.adjustAttendanceRecord(attendanceRecord));
        repository.save(attendanceRecords);
    }
}

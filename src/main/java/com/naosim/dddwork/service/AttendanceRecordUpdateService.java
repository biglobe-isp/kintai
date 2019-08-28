package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.rules.OverTimeRule;
import com.naosim.dddwork.domain.time.WorkingDuration;


public class AttendanceRecordUpdateService {

    public AttendanceRecordUpdateService()
    {}

    public boolean executeService(AttendanceRecord attendanceRecord)
    {
        WorkingDuration workingDuration = attendanceRecord.getWorkingDuration();
        // create OverTime Rule
        workingDuration =  OverTimeRule.checkServiceOverTime(workingDuration);


       // load data repository
       AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        AttendanceRecords attendanceRecords = repository.load();

        //  update data repository
        attendanceRecords.insert(attendanceRecord);
        return repository.save();
    }
}

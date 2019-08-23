package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.date.Day;
import com.naosim.dddwork.domain.date.Month;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.Year;
import com.naosim.dddwork.domain.rules.OverTimeRule;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.WorkingDuration;

import java.util.TreeMap;

public class AttendanceRecordUpdateService {

    public AttendanceRecordUpdateService()
    {}

    public boolean executeService(AttendanceRecord attendanceRecord)
    {
        WorkingDuration workingDuration = attendanceRecord.getWorkingDuration();
        // create OverTime Rule
        OverTimeRule overTimeRule = new OverTimeRule();
        workingDuration =  overTimeRule.checkServiceOverTime(workingDuration);


       // load data repository
       AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        AttendanceRecords attendanceRecords = repository.load();

        //  update data repository
        attendanceRecords.insert(attendanceRecord);
        return repository.save();
    }
}

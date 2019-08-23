package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.YearMonth;
import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.RegularTimeRule;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.WorkingDuration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class AttendanceRecords {

    ArrayList<AttendanceRecord> records;

    public AttendanceRecords()
    {
        records = new ArrayList<AttendanceRecord>();
    }

    public void insert(AttendanceRecord attendanceRecord)
    {
       records.add(attendanceRecord) ;
    }
    public List<AttendanceRecord> getAttendanceRecords() { return records;}


}

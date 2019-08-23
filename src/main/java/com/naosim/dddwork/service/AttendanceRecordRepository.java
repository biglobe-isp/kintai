package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.YearMonth;
import com.naosim.dddwork.domain.time.WorkingDuration;

import java.util.TreeMap;

public interface AttendanceRecordRepository {

    public AttendanceRecords load();
    public AttendanceRecords load(YearMonth yearMonth);
    public boolean save();
}

package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.time.WorkingDuration;

import java.util.TreeMap;

public interface AttendanceRecordRepository {

    public TreeMap<WorkingDate, WorkingDuration> load();
    public boolean save();
}

package com.naosim.dddwork.domain;
import java.util.ArrayList;
import java.util.List;

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

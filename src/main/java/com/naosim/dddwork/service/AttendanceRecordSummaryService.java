package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.date.YearMonth;



public class AttendanceRecordSummaryService {

    // TODO argument should be YearMonth class (DONE)
    public AttendanceSummary executeService(YearMonth yearMonth)
    {
        /* TODO create YearMonth class (DONE) */
        int dateKey = yearMonth.getValue() ;

        //  load data repository
        AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        // TODO TreeMap -> domain class (DONE)
        AttendanceRecords attendanceRecords = repository.load(yearMonth);

        AttendanceSummary attendanceSummary  = new AttendanceSummary(attendanceRecords);
        return attendanceSummary; // <-- TODO return domain object as result
    }


}

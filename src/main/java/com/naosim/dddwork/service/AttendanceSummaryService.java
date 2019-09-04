package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.AttendanceSummaryCalculator;
import com.naosim.dddwork.domain.Fired;
import com.naosim.dddwork.domain.date.YearMonth;
import io.vavr.control.Either;

public class AttendanceSummaryService {
    public Either<Fired, AttendanceSummary> summary(YearMonth yearMonth) {
        AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();

        AttendanceRecords attendanceRecords = repository.load(yearMonth);
        return AttendanceSummaryCalculator.calculate(attendanceRecords);
    }
}

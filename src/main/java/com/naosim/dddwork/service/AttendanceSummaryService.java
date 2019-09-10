package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.AttendanceSummaryCalculator;
import com.naosim.dddwork.domain.AttendanceSummaryFailed;
import com.naosim.dddwork.domain.date.YearMonth;
import io.vavr.control.Either;

public class AttendanceSummaryService {
    private static final AttendanceSummaryCalculator attendanceSummaryCalculator = new AttendanceSummaryCalculator();

    public Either<AttendanceSummaryFailed, AttendanceSummary> summary(YearMonth yearMonth) {
        AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();

        AttendanceRecords attendanceRecords = repository.load(yearMonth);
        return attendanceSummaryCalculator.calculate(attendanceRecords);
    }
}

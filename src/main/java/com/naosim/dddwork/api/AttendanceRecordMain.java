package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.AttendanceSummaryFailed;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.YearMonth;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.WorkingDuration;
import com.naosim.dddwork.service.AttendanceRecordUpdateService;
import com.naosim.dddwork.service.AttendanceSummaryService;
import io.vavr.control.Either;

public class AttendanceRecordMain {
    private AttendanceSummaryService attendanceSummaryService = new AttendanceSummaryService();

    public String command(String[] args) {
        if (args.length == 4 && args[0].toUpperCase().equals("UPDATE")) {
            // TODO parameter validation for another 3 , maybe spring parameter annotation? in command line parser
            AttendanceRecordUpdateService service = new AttendanceRecordUpdateService();
            WorkingDate workingDate = CommandLineParser.parseWorkingDate(args[1]);
            EntryTime startTime = CommandLineParser.parseEntryTime(args[2]);
            EntryTime endTime = CommandLineParser.parseEntryTime(args[3]);
            WorkingDuration workingDuration = new WorkingDuration(startTime, endTime);
            AttendanceRecord attendanceRecord = new AttendanceRecord(workingDate, workingDuration);
            service.update(attendanceRecord);
            return "Date:" + args[1] + " attendance record is updated successfully.";
        } else if (args.length == 3 && args[0].toUpperCase().equals("SUMMARY")) {
            // TODO parameter validation for another one
            YearMonth yearMonth = CommandLineParser.parseYearMonth(args[1] + args[2]);
            Either<AttendanceSummaryFailed, AttendanceSummary> attendanceSummary = attendanceSummaryService.summary(
                    yearMonth);
            return attendanceSummary.fold(
                    x -> "You are FIRED!",
                    x -> "Regular Working Hours " + x.getRegularWorkingHours() + ":" + x.getRegularWorkingMinutes() +
                            " Over Time Working Hours " + x.getOverTimeWorkingHours() + ":" + x.getOverTimWorkingMinutes()
            );
        } else {
            return "Usage : java AttendanceRecordMain update YYYYMMDD  HHMM HHMM" +
                    "        java AttendanceRecordMain summary YYYY MM";
        }
    }
}

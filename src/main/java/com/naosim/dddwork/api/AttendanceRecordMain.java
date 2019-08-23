package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.YearMonth;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.WorkingDuration;
import com.naosim.dddwork.service.AttendanceRecordSummaryService;
import com.naosim.dddwork.service.AttendanceRecordUpdateService;

import javax.swing.*;

public class AttendanceRecordMain {

    public static void main(String args[])
    {
        // check mode
        if (args[0].toUpperCase().equals("UPDATE") && args.length == 4)
        {
            AttendanceRecordUpdateService service = new AttendanceRecordUpdateService();
            WorkingDate workingDate =  CommandLineParser.parseWorkingDate(args[1]);
            EntryTime startTime = CommandLineParser.parseEntryTime(args[2]);
            EntryTime endTime = CommandLineParser.parseEntryTime(args[3]);
            WorkingDuration workingDuration = new WorkingDuration(startTime,endTime);
            AttendanceRecord attendanceRecord = new AttendanceRecord(workingDate,workingDuration);
            boolean success =  service.executeService(attendanceRecord);

            if(success)
            {
               System.out.println("Date:" + args[1] + " attendance record is updated successfully.");
            }
            else
            {
                System.out.println("Attendance record update failed.");
            }
        }
        else if (args[0].toUpperCase().equals("SUMMARY") && args.length == 3)
        {
            AttendanceRecordSummaryService service = new AttendanceRecordSummaryService();
            YearMonth yearMonth = CommandLineParser.parseYearMonth(args[2] + args[3]);
            AttendanceSummary attendanceSummary = service.executeService(yearMonth);
            // TODO : display result
        }
        else
        {
            System.out.println("Usage : java AttendanceRecordMain update YYYYMMDD  HHMM HHMM");
            System.out.println("        java AttendanceRecordMain summary YYYY MM");
        }
    }
}

package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.service.AttendanceRecordSummaryService;
import com.naosim.dddwork.service.AttendanceRecordUpdateService;

public class AttendanceRecordMain {

    public static void main(String args[])
    {
        // check mode
        if (args[0].toUpperCase().equals("UPDATE") && args.length == 4)
        {
            AttendanceRecordUpdateService service = new AttendanceRecordUpdateService();
            boolean success =  service.executeService(args[1], args[2], args[3]);
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
            AttendanceSummary summary = service.executeService(args[1],args[2]);
            if(summary != null)
            {
                System.out.println("Regular Working Hours - " + summary.regularTime.toHours() + ":" + summary.regularTime.toMinutes()%60 +
                        " OverTime - " + summary.overTime.toHours() + ":" + summary.overTime.toMinutes() %60);
            }
        }
        else
        {
            System.out.println("Usage : java AttendanceRecordMain update YYYYMMDD  HHMM HHMM");
            System.out.println("        java AttendanceRecordMain summary YYYY MM");
        }
    }
}

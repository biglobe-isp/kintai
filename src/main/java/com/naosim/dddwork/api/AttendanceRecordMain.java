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
            System.out.println(service.executeService(args[1],args[2]));
        }
        else
        {
            System.out.println("Usage : java AttendanceRecordMain update YYYYMMDD  HHMM HHMM");
            System.out.println("        java AttendanceRecordMain summary YYYY MM");
        }
    }
}

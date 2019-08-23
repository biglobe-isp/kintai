package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.BreakTimeRules;
import com.naosim.dddwork.domain.rules.RegularTimeRule;
import com.naosim.dddwork.domain.time.EntryTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class  AttendanceSummary
{
    private Duration regularTime = null;
    private Duration overTime = null;
    private boolean fired;



    public AttendanceSummary(AttendanceRecords attendanceRecords)
    {
        regularTime =  Duration.ofMinutes(0);
        overTime =  Duration.ofMinutes(0);
        fired = false;

        // calculate summary by records
        for(AttendanceRecord attendanceRecord : attendanceRecords.getAttendanceRecords()) {
            // TODO : check fire case (late in the office)
            calculateSummary(attendanceRecord);
        }
    }


    // TODO segregate isFired() and addSummary - return value should have meaningful value - not only true/false
    private void calculateSummary(AttendanceRecord attendanceRecord)
    {
        // get startTime & endTime
        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime endTime = attendanceRecord.getWorkingDuration().getEndTime();

        // calculate working hours
        Duration workingHours = calculateDuration(attendanceRecord.getWorkingDate(),startTime,endTime);

        // extract break times
        for(BreakTimeRules r : BreakTimeRules.values())
        {
            BreakTimeRule rule = r.getBreakTimeRule();
            Duration breakDuration = null;
            if(endTime.getValue()  >= rule.getEndTime().getValue())
            {
                // break time is fully contained with working hours
                breakDuration = calculateDuration(attendanceRecord.getWorkingDate(), rule.getStartTime(), rule.getEndTime());
            }
            else if( endTime.getValue() >= rule.getStartTime().getValue() &&
                    endTime.getValue() < rule.getEndTime().getValue())
            {
                // break time is partially contained with working hours
                breakDuration = calculateDuration(attendanceRecord.getWorkingDate(), rule.getStartTime(), endTime);
            }
            if(breakDuration != null)
            {
                workingHours = workingHours.minus(breakDuration);
            }
        }

        //breakTimeRulesVector.stream().filter((r) ->  { return endTime.getValue() > r.getEndTime().getValue(); })
        //        .forEach();

        // add working hours which are regular-time and over-time separately into the summary
        if(workingHours.toMinutes() > RegularTimeRule.REGULAR_WORKING_MINUTES )  // more than 8 hours
        {
            regularTime = regularTime.plus(Duration.ofHours(RegularTimeRule.REGULAR_WORKING_HOURS));
            Duration overtime = workingHours.minus(Duration.ofHours(RegularTimeRule.REGULAR_WORKING_HOURS));
            overTime = overTime.plus(overtime);
        }
        else
        {
            regularTime = regularTime.plus(workingHours);
        }
    }


    private Duration calculateDuration(WorkingDate workingDate, EntryTime startTime, EntryTime endTime)
    {
        int year = workingDate.getYear().getYear();
        int month = workingDate.getMonth().getMonth();
        int day = workingDate.getDay().getDay();
        LocalDateTime from = LocalDateTime.of(year, month, day, startTime.getHour().getHour() , startTime.getMinute().getMinute());
        LocalDateTime to = LocalDateTime.of(year, month, day, endTime.getHour().getHour() , endTime.getMinute().getMinute());
        Duration duration = Duration.between(from, to);
        return duration;
    }

    public String toString()
    {
        return regularTime.toHours() + ":" + regularTime.toMinutes() % 60 + "/" +  overTime.toHours() + ":" + overTime.toMinutes() % 60;
    }
}
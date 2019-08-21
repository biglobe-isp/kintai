package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.BreakTimeRules;
import com.naosim.dddwork.domain.rules.RegularTimeRule;
import com.naosim.dddwork.domain.time.RecordedTime;
import com.naosim.dddwork.domain.time.WorkingDuration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;


public class AttendanceRecordSummaryService {

    private AttendanceSummary attendanceSummary;

    private BreakTimeRules breakTimeRules;
    private RegularTimeRule regularTimeRule;
    private Vector<BreakTimeRule> breakTimes;

    public AttendanceRecordSummaryService() {

        // create attendance summary for output of this service
        attendanceSummary = new AttendanceSummary();

        // create regular working hour rule
        regularTimeRule = new RegularTimeRule();

        // create break time rules
        breakTimeRules = new BreakTimeRules();
        breakTimes = breakTimeRules.getBreakTimeRules();
    }

    public AttendanceSummary executeService(String yearParam, String monthParam)
    {
        // construct  Working Year and Date ( e.g. 2019/12 -> 201912 )
        int dateKey = Integer.parseInt(yearParam) * 100 + Integer.parseInt(monthParam);

        //  load data repository
        AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        TreeMap<WorkingDate, WorkingDuration> attendanceRecords = repository.load();

        // iterate the data to calculate regular working hours and overtime
        Set<WorkingDate> keys = attendanceRecords.keySet();
        attendanceRecords.forEach((k, v) -> {
            if (k.getYearMonth() == dateKey) {
                boolean ret = addSummary(k, v);
                if (!ret) {
                    // You Are FIRED!
                    attendanceSummary.fired = true;
                }
            }
        });
        return attendanceSummary;
}

    private boolean addSummary(WorkingDate workingDate,WorkingDuration workingDuration)
    {
        // get startTime & endTime
        RecordedTime startTime = workingDuration.getStartTime();
        RecordedTime endTime = workingDuration.getEndTime();

        // special quote - if the employee is late in the office, simply fire this guy.
        if(startTime.getValue() > regularTimeRule.getStartTime().getValue())
        {
            System.out.println("You are FIRED!!!! ");
            return false;
        }

        // calculate working hours
        Duration workingHours = calculateDuration(workingDate,startTime,endTime);

        // extract break times
        for(BreakTimeRule rule : breakTimes)
        {
            Duration breakDuration = null;
            if(endTime.getValue()  >= rule.getEndTime().getValue())
            {
                // break time is fully contained with working hours
                breakDuration = calculateDuration(workingDate, rule.getStartTime(), rule.getEndTime());
            }
            else if( endTime.getValue() >= rule.getStartTime().getValue() &&
                    endTime.getValue() < rule.getEndTime().getValue())
            {
                // break time is partially contained with working hours
                breakDuration = calculateDuration(workingDate, rule.getStartTime(), endTime);
            }
            if(breakDuration != null)
            {
                workingHours = workingHours.minus(breakDuration);
            }
        }

        // add working hours which are regular-time and over-time separately into the summary
        if(workingHours.toMinutes() > RegularTimeRule.REGULAR_WORKING_MINUTES )  // more than 8 hours
        {
            attendanceSummary.regularTime = attendanceSummary.regularTime.plus(Duration.ofHours(RegularTimeRule.REGULAR_WORKING_HOURS));
            Duration overtime = workingHours.minus(Duration.ofHours(RegularTimeRule.REGULAR_WORKING_HOURS));
            attendanceSummary.overTime = attendanceSummary.overTime.plus(overtime);
        }
        else
        {
            attendanceSummary.regularTime = attendanceSummary.regularTime.plus(workingHours);
        }
        return true;
    }

    private Duration calculateDuration(WorkingDate workingDate,RecordedTime startTime, RecordedTime endTime)
    {
        int year = workingDate.getYear();
        int month = workingDate.getMonth();
        int day = workingDate.getDay();
        LocalDateTime from = LocalDateTime.of(year, month, day, startTime.getHour().getHour() , startTime.getMinute().getMinute());
        LocalDateTime to = LocalDateTime.of(year, month, day, endTime.getHour().getHour() , endTime.getMinute().getMinute());
        Duration duration = Duration.between(from, to);
        return duration;
    }
}

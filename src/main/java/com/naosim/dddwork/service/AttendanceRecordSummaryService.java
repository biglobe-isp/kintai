package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.date.Month;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.Year;
import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.BreakTimeRules;
import com.naosim.dddwork.domain.rules.RegularTimeRule;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.RecordedTime;
import com.naosim.dddwork.domain.time.WorkingDuration;
import groovy.lang.Tuple;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeMap;


public class AttendanceRecordSummaryService {


    private class  AttendanceSummary
    {
        public int regularHours;
        public int regularMinutes;

        public int overtimeHours;
        public int overtimeMinutes;
    }

    private class Duratioin {
        public int hour;
        public int minute;
    }

    private AttendanceSummary summary;

    private BreakTimeRules breakTimeRules;
    private RegularTimeRule regularTimeRule;
    private boolean employeeFired  = false;

    public AttendanceRecordSummaryService(String yearParam, String monthParam)
    {
        summary = new AttendanceSummary();

        // create regular working hour rule
        regularTimeRule = new  RegularTimeRule();

        // create break time rules
        breakTimeRules = new BreakTimeRules();

        // construct  Working Year and Date ( e.g. 2019/12 -> 201912 )
        int dateKey = Integer.parseInt(yearParam) * 100 + Integer.parseInt(monthParam);

        //  load data repository
        AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        TreeMap<WorkingDate, WorkingDuration> attendanceRecords = repository.load();

        // iterate the data to calculate regular working hours and overtime
        Set<WorkingDate> keys = attendanceRecords.keySet();
        for (WorkingDate key : keys)
        {
            if (key.getYearMonth() == dateKey)
            {
                addSummary(key,attendanceRecords.get(key));
            }
        }

    }

    private void addSummary(WorkingDate workingDate,WorkingDuration workingDuration)
    {
        // get startTime & endTime
        RecordedTime startTime = workingDuration.getStartTime();
        RecordedTime endTime = workingDuration.getEndTime();

        // special quote - if the employee is late in the office, simply fire this guy.
        if(startTime.getValue() > regularTimeRule.getStartTime().getValue())
        {
           System.out.println("You are FIRED!!!! ");
           employeeFired = true;
           return ;
        }


    }

    private boolean isEmployeeFired()
    {
        return  employeeFired;
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

package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.BreakTimeRules;
import com.naosim.dddwork.domain.rules.RegularTimeRule;
import com.naosim.dddwork.domain.time.EntryTime;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.time.Duration.between;
import static java.time.Duration.ofMinutes;


public class  AttendanceSummary
{
    @Getter
    private Duration regularTime = null;
    @Getter
    private Duration overTime = null;
    private boolean fired;
    private static int regularWorkingMinutes;

    public AttendanceSummary(AttendanceRecords attendanceRecords)
    {
        regularTime = ofMinutes(0);
        overTime =  ofMinutes(0);
        fired = false;

        // calculate regular working hours in minutes
        EntryTime regularWorkingDuration = RegularTimeRule.REGULAR_WORKING_DURATION.getEntryTime();
        regularWorkingMinutes = regularWorkingDuration.getHour().getHour() * 60 + regularWorkingDuration.getMinute().getMinute();

        // calculate summary by records
        for(AttendanceRecord attendanceRecord : attendanceRecords.getAttendanceRecords()) {
            // TODO : check fired case (late in the office)
            fired = checkFired(attendanceRecord);
            if(fired)
            {
                return ; // stop calculating the summary
            }
            // add working time to private variable regularTime and overTime
            Duration totalWorkingHours  = calculateTotalWorkingHours(attendanceRecord);

            Duration regularWorkingHours = calculateRegularWorkingHours(totalWorkingHours);
            regularTime = regularTime.plus(regularWorkingHours);

            Duration overtimeWorkingHours = calculateOvertimeWorkingHours(totalWorkingHours);
            overTime = overTime.plus(overtimeWorkingHours);
        }
    }


    public boolean isFired()
    {
        return fired;
    }

    public boolean checkFired(AttendanceRecord attendanceRecord)
    {
        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime regularStatTime = RegularTimeRule.REGULAR_TIME_START.getEntryTime();
        if(startTime.getValue() >  regularStatTime.getValue())
        {
            // late in the office, this guy is FIRED!!!!
            return true;
        }
        return false;
    }

    Duration calculateTotalWorkingHours(AttendanceRecord attendanceRecord) {

        // get startTime & endTime
        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime endTime = attendanceRecord.getWorkingDuration().getEndTime();

        // calculate working hours
        Duration workingHours = calculateDuration(attendanceRecord.getWorkingDate(), startTime, endTime);

        long breakMinutes = Arrays.stream(BreakTimeRules.values()).
                map(r -> r.getBreakTimeRule()).
                map(rule -> getBreakDuration(attendanceRecord.getWorkingDate(), endTime, rule)).
                filter(Objects::nonNull).
                collect(Collectors.summingLong(d -> d.toMinutes()));
        workingHours = workingHours.minus( ofMinutes(breakMinutes));
        return workingHours;
    }

    Duration getBreakDuration (WorkingDate workingDate,EntryTime endTime,BreakTimeRule breakTimeRule)
    {
        if (endTime.getValue() >= breakTimeRule.getEndTime().getValue()) {
            // break time is fully contained with working hours
            return calculateDuration( workingDate, breakTimeRule.getStartTime(), breakTimeRule.getEndTime());
        } else if (endTime.getValue() >= breakTimeRule.getStartTime().getValue() &&
                endTime.getValue() < breakTimeRule.getEndTime().getValue()) {
            // break time is partially contained with working hours
            return calculateDuration( workingDate, breakTimeRule.getStartTime(), endTime);
        }
        return null;

    }

    Duration calculateRegularWorkingHours(Duration totalWorkingHours)
    {
        return totalWorkingHours.toMinutes() > regularWorkingMinutes ?
                ofMinutes(regularWorkingMinutes) : totalWorkingHours ;
    }

    Duration calculateOvertimeWorkingHours(Duration totalWorkingHours)
    {
        return totalWorkingHours.toMinutes() > regularWorkingMinutes ?
             totalWorkingHours.minus(ofMinutes(regularWorkingMinutes)) : ofMinutes(0) ;
    }


    private Duration calculateDuration(WorkingDate workingDate, EntryTime startTime, EntryTime endTime)
    {
        int year = workingDate.getYear().getYear();
        int month = workingDate.getMonth().getMonth();
        int day = workingDate.getDay().getDay();
        int endHour = endTime.getHour().getHour();
        LocalDateTime from = LocalDateTime.of(year, month, day, startTime.getHour().getHour() , startTime.getMinute().getMinute());
        LocalDateTime to;
        if(endHour >= 24) //  over
        {
           // increase day ++
            to = from.plusDays(1);
            year = to.getYear();
            month = to.getMonthValue();
            day = to.getDayOfMonth();
            endHour = 0;
        }
        to = LocalDateTime.of(year, month, day, endHour , endTime.getMinute().getMinute());
        Duration duration = between(from, to);
        return duration;
    }

    public String toString()
    {
        return regularTime.toHours() + ":" + regularTime.toMinutes() % 60 + "/" +  overTime.toHours() + ":" + overTime.toMinutes() % 60;
    }
}
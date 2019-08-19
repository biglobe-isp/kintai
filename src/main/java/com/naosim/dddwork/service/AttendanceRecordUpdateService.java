package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV;
import com.naosim.dddwork.domain.date.Day;
import com.naosim.dddwork.domain.date.Month;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.Year;
import com.naosim.dddwork.domain.rules.OverTimeRule;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.RecordedTime;
import com.naosim.dddwork.domain.time.WorkingDuration;

import java.util.HashMap;
import java.util.TreeMap;

public class AttendanceRecordUpdateService {

    private final int FinalCutHour = 24;
    private final int FinalCutMinutes = 0;

    HashMap<WorkingDate,WorkingDuration> attendanceRecords;
    public AttendanceRecordUpdateService(String workingDateParam, String startTimeParam, String endTimeParam)
    {
        // construct  WorkingDate
        WorkingDate workingDate = parseWorkingDate(workingDateParam);

        // construct WorkingHours
        RecordedTime startTime = parseTime(startTimeParam);
        RecordedTime endTime = parseTime(endTimeParam);

        // insert OverTime Rule
        OverTimeRule overTimeRule = new OverTimeRule(
                new RecordedTime( new Hour(FinalCutHour), new Minute(FinalCutMinutes)));

        // validator working hours
        // 1.if end time > AM00:00 , cut off the rest of working time  after 24:00
        // 2. if end time < start time, assume end the time is on the next day , cut off at 24:00
        if(endTime.getValue() > overTimeRule.getFinalCutTime().getValue())
        {
            endTime = overTimeRule.getFinalCutTime();
        }

        if(startTime.getValue() > endTime.getValue())
        {
            endTime = overTimeRule.getFinalCutTime();
        }

        // create workingDuration
        WorkingDuration workingDuration = new WorkingDuration(startTime, endTime);

       // load data repository
       AttendanceRecordRepository repository = new AttendanceRecordRepositoryCSV();
        TreeMap<WorkingDate,WorkingDuration> attendanceRecords = repository.load();

       //  update data repository
        attendanceRecords.put(workingDate,workingDuration);
        repository.save();
    }

    private WorkingDate  parseWorkingDate(String param)
    {
        Year year = new Year(Integer.parseInt(param.substring(0,4)));
        Month month = new Month(Integer.parseInt(param.substring(4,6)));
        Day day = new Day(Integer.parseInt(param.substring(6,8)));
        return new WorkingDate(year,month,day);
    }

    private RecordedTime parseTime(String param)
    {
       Hour hour = new Hour(Integer.parseInt(param.substring(0,2))) ;
       Minute minute = new Minute(Integer.parseInt(param.substring(2,4)));
       return  new RecordedTime(hour,minute);
    }
}

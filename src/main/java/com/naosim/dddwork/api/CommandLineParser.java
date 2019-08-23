package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.date.*;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;

public class CommandLineParser {

    static WorkingDate parseWorkingDate(String date) // format YYYYMMDD
    {
        try {
            return getWorkingDate(date);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("Can't convert parameter "+ date +" to WorkingDate object");
        }

    }

    public static WorkingDate getWorkingDate(String date) {
        Year year = new Year(Integer.parseInt(date.substring(0, 4)));
        Month month = new Month(Integer.parseInt(date.substring(4,6)));
        Day day = new Day(Integer.parseInt(date.substring(6,8)));
        return new WorkingDate(year,month,day);
    }

    static EntryTime parseEntryTime(String time)
    {

        try {
            Hour hour = new Hour(Integer.parseInt(time.substring(0,2)));
            Minute minute = new Minute(Integer.parseInt(time.substring(2,4)));
            return  new EntryTime(hour,minute);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("can't convert parameter " + time + " to EntryTime object");
        }
    }

    static YearMonth parseYearMonth(String yearMonth)
    {
        try {
           Year year = new Year(Integer.parseInt(yearMonth.substring(0,4)));
           Month month = new Month(Integer.parseInt(yearMonth.substring(4,6)));
           return new YearMonth(year,month);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("can't convert parameter " + yearMonth + " to YearMonth object.");
        }
    }

}

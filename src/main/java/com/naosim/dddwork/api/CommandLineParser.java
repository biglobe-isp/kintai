package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.date.*;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;

class CommandLineParser {
    static WorkingDate parseWorkingDate(String date) // format YYYYMMDD
    {
        return getWorkingDate(date);
    }

    private static WorkingDate getWorkingDate(String date) {
        // TODO message validation
        Year year = new Year(Integer.parseInt(date.substring(0, 4)));
        Month month = new Month(Integer.parseInt(date.substring(4, 6)));
        Day day = new Day(Integer.parseInt(date.substring(6, 8)));
        return new WorkingDate(year, month, day);
    }

    static EntryTime parseEntryTime(String time) {

        Hour hour = new Hour(Integer.parseInt(time.substring(0, 2)));
        Minute minute = new Minute(Integer.parseInt(time.substring(2, 4)));
        return new EntryTime(hour, minute);
    }

    static YearMonth parseYearMonth(String yearMonth) {
        Year year = new Year(Integer.parseInt(yearMonth.substring(0, 4)));
        Month month = new Month(Integer.parseInt(yearMonth.substring(4, 6)));
        return new YearMonth(year, month);
    }
}

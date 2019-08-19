package com.naosim.dddwork.domain.date;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class WorkingDate implements Comparable<WorkingDate> {
    private final Year year;
    private final Month month;
    private final Day day;

    public WorkingDate(Year year, Month month, Day day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYearMonth()
    {
        return  (year.getYear() * 100) + month.getMonth();
    }

    public  int getValue()
    {
        return (year.getYear() * 10000) + (month.getMonth()*100) + day.getDay();
    }


    @Override
    public int compareTo(WorkingDate o) {
        return this.getValue() - o.getValue();
    }

    public String toString()
    {
        return String.valueOf(getValue());
    }
}

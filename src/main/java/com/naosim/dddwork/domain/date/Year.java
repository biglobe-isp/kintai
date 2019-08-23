package com.naosim.dddwork.domain.date;

import lombok.Getter;


public class Year {
    @Getter
    private final int year;

    public Year(int year)
    {
       if(!is21stCentury(year))
       {
           throw new RuntimeException("This system must retire before 22nd Century coming!!");
       }
       this.year = year;
    }

    // !!! this should retire before 22nd century
    private boolean is21stCentury(int year)
    {
        if(year < 2000 || year > 2099)
        {
            return false;
        }
        return true;
    }

}

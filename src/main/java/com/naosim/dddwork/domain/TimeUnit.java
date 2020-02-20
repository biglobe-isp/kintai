package com.naosim.dddwork.domain;

import com.google.common.base.Strings;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
public class TimeUnit {
    int hour;
    int minutes;

    public TimeUnit(String inputTime) {
        if (!isTimeValue(inputTime)) {
            throw new RuntimeException("Invalid time.");
        }
        this.hour = Integer.parseInt(inputTime.substring(0, 2));
        this.minutes = Integer.parseInt(inputTime.substring(2));
    }

    private boolean isTimeValue(String inputTime) {
        if (Strings.isNullOrEmpty(inputTime))
            return false;
        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-9])[0-5][0-9]$");
        Matcher m = p.matcher(inputTime);
        if ( !m.find() )
            return false;
        return true;
    }
}

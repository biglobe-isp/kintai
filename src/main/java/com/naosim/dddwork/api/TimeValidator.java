package com.naosim.dddwork.api;

import com.google.common.base.Strings;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value(staticConstructor = "of")
public class TimeValidator {
    String value;

    public boolean isValid() {
        if (Strings.isNullOrEmpty(value))
            return false;
        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-9])[0-5][0-9]$");
        Matcher m = p.matcher(value);
        if ( !m.find() )
            return false;
        return true;
    }
}

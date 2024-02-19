package com.kintai.datasource.value.expansion.starttime;

import com.kintai.datasource.value.StartTime;
import com.kintai.exception.ValidatorException;

public class StartTimeExtend extends StartTime {
    private final String parseStartTimeString = "-start:";

    private final String parseUnderBar = "_";

    public StartTimeExtend(String startTime) throws ValidatorException {
        super(startTime);
        this.startTime = parseStartTime(startTime);
    }

    protected String parseStartTime(String startTime) {
        return startTime.replace(parseStartTimeString, "").replace(parseUnderBar, "");
    }

    @Override
    protected void isTime(String startTime) throws ValidatorException {
        String parsedStartTime = parseStartTime(startTime);
        super.isTime(parsedStartTime);
    }
}

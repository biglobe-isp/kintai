package com.kintai.datasource.value.expansion.endtime;

import com.kintai.datasource.value.EndTime;
import com.kintai.exception.ValidatorException;

public class EndTimeExtend extends EndTime {
    private final String parseEndTimeString = "-end:";

    private final String parseUnderBar = "_";

    public EndTimeExtend(String endTime) throws ValidatorException {
        super(endTime);
        this.endTime = parseEndTime(endTime);
    }

    protected String parseEndTime(String endTime) {
        return endTime.replace(parseEndTimeString, "").replace(parseUnderBar, "");
    }

    @Override
    protected void isTime(String endTime) throws ValidatorException {
        String parsedEndTime = parseEndTime(endTime);
        super.isTime(parsedEndTime);
    }
}

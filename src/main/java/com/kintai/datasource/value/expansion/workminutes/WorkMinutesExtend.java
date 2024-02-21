package com.kintai.datasource.value.expansion.workminutes;

import com.kintai.datasource.value.WorkDate;
import com.kintai.datasource.value.WorkMinutes;
import com.kintai.datasource.value.WorkTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WorkMinutesExtend extends WorkMinutes {
    private final String dateFormatPattern = "yyyyMMdd";

    private final String compareDate = "20240315";

    public WorkMinutesExtend(WorkTime workTime, WorkDate workDate) throws ParseException {
        super(workTime);
        if(isCompareDate(workDate)) {
            this.workMinutes -= calculateAddRest(workTime.getEndTime().getEndTime());
        }
    }

    protected boolean isCompareDate(WorkDate workDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        return dateFormat.parse(workDate.getWorkDate()).compareTo(dateFormat.parse(compareDate)) != -1;
    }

    protected int calculateAddRest(String endTime) {
        int endH = parseInt(endTime.substring(0, 2));
        int endM = parseInt(endTime.substring(2, 4));
        int minusWorkMinutes = 0;
        if (endH == 15) {
            minusWorkMinutes = endM;
        } else if (endH >= 16) {
            minusWorkMinutes = 60;
        }
        return minusWorkMinutes;
    }

}

package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.datasource.AppConst;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.OfficeMinutes;
import jp.co.esumit.kintai.domain.field.StartTime;

public class CalcOfficeMinutes {

    public OfficeMinutes getOfficeMinutes(StartTime startTime, EndTime endtime) {

        int startH = startTime.getStartHr().getValue();
        int startM = startTime.getStartMin().getValue();
        int endH = endtime.getEndHr().getValue();
        int endM = endtime.getEndMin().getValue();

        int officeMinutes = endH * 60 + endM - (startH * 60 + startM);


        for (int[] breakTime : AppConst.BREAK_TIMES) {
            if (endH == breakTime[0]) {
                officeMinutes -= endM;
            } else if (endH >= breakTime[1]) {
                officeMinutes -= 60;
            }
        }

        return new OfficeMinutes(officeMinutes);
    }
}

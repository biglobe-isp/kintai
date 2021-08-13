package jp.co.esumit.kintai.domain;

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

        if (endH == 12) {
            officeMinutes -= endM;
        } else if (endH >= 13) {
            officeMinutes -= 60;
        }

        if (endH == 18) {
            officeMinutes -= endM;
        } else if (endH >= 19) {
            officeMinutes -= 60;
        }

        if (endH == 21) {
            officeMinutes -= endM;
        } else if (endH >= 22) {
            officeMinutes -= 60;
        }
        return new OfficeMinutes(officeMinutes);
    }
}

package refoctor.api;

import refoctor.domain.japan.DateDomain;
import refoctor.domain.japan.EndTime;
import refoctor.domain.japan.Form.*;
import refoctor.domain.japan.StartTime;

import java.time.LocalDateTime;

public class ChangeForm {

    public StartTime getStart(String start) {
        Start startSt = new Start(getStartSt(start));
        StartHour startHour = new StartHour(getHour(startSt.getValue()));
        StartMinutes startMinutes = new StartMinutes(getMinutes(startSt.getValue()));

        return new StartTime(startSt, startHour, startMinutes);
    }

    public EndTime getEnd(String end) {
        End endSt = new End(getEndSt(end));
        EndHour endHour = new EndHour(getHour(endSt.getValue()));
        EndMinutes endMinutes = new EndMinutes(getMinutes(endSt.getValue()));

        return new EndTime(endSt, endHour, endMinutes);
    }

    public DateDomain getDateDomain(String date) {
        Date dateSt = new Date(getDateSt(date));
        NowTime nowTime = new NowTime(LocalDateTime.now());

        return new DateDomain(dateSt, nowTime);
    }

    private int getHour(String time) {
        return Integer.valueOf(time.substring(0, 2));
    }

    private int getMinutes(String time) {
        return Integer.valueOf(time.substring(2, 4));
    }

    private String getStartSt(String start) {
        return start.substring(7);
    }

    private String getEndSt(String end) {
        return end.substring(5);
    }

    private String getDateSt(String date) {
        return date.substring(6);
    }
}

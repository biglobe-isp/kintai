package api;

import domain.ValueForm.*;
import domain.DateDomain;
import domain.EndDomain;
import domain.StartDomain;

public class ChangeForm {

    public StartDomain getStart(String start) {
        Start startSt = new Start(getStartSt(start));
        StartH startH = new StartH(getHour(startSt.getStartVal()));
        StartM startM = new StartM(getMinutes(startSt.getStartVal()));

        return new StartDomain(startSt, startH, startM);
    }

    public EndDomain getEnd(String end) {
        End endSt = new End(getEndSt(end));
        EndH endH = new EndH(getHour(endSt.getEndVal()));
        EndM endM = new EndM(getMinutes(endSt.getEndVal()));

        return new EndDomain(endSt, endH, endM);
    }

    public DateDomain getDate(String date) {
        Date dateSt = new Date(getDateSt(date));
        Now now = new Now();

        return new DateDomain(dateSt, now);
    }

    private int getHour(String time) { return Integer.valueOf(time.substring(0, 2)); }

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

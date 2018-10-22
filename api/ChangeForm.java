package api;

import datasource.Now;
import domain.ValueForm.*;
import domain.DateDomain;
import domain.EndDomain;
import domain.StartDomain;

public class ChangeForm {

    public StartDomain getStart(String start) {
        Start startSt = new Start(getStartSt(start));
        StartH startH = new StartH(getHour(startSt.getValue()));
        StartM startM = new StartM(getMinutes(startSt.getValue()));

        return new StartDomain(startSt, startH, startM);
    }

    public EndDomain getEnd(String end) {
        End endSt = new End(getEndSt(end));
        EndH endH = new EndH(getHour(endSt.getValue()));
        EndM endM = new EndM(getMinutes(endSt.getValue()));

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

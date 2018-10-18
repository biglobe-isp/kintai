package api;

import domain.*;

import java.time.LocalDateTime;

public class ChangeForm {

    public StartDomain getStart(String start) {
        return new StartDomain(getStartSt(start), getHour(start), getMinutes(start));
    }

    public EndDomain getEnd(String end) {
        return new EndDomain(getEndSt(end), getHour(end), getMinutes(end));
    }

    public DateDomain getDate(String date) {
        return new DateDomain(getDateSt(date), LocalDateTime.now().toString());
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

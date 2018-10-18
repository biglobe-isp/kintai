package domain;

import java.time.LocalDateTime;

public class DateDomain {

    private final String date;
    private final String now;

    public DateDomain(String date, String now) {

        this.date = date;
        this.now = now;
    }


    public String getDate() {
        return date;
    }

    public String getNow() {
        return now;
    }
}

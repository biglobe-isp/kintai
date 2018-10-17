package domain;

import java.time.LocalDateTime;

public class DateDomain {

    private final String date;
    private final String now = LocalDateTime.now().toString();

    public DateDomain(String date) {
        this.date = date.substring(6);
    }


    public String getDate() {
        return date;
    }

    public String getNow() {
        return now;
    }
}

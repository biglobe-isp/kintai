package domain;

import domain.ValueForm.Date;
import domain.ValueForm.Now;

public class DateDomain {

    private final Date date;
    private final Now now;

    public DateDomain(Date date, Now now) {

        this.date = date;
        this.now = now;
    }


    public Date getDate() {
        return date;
    }

    public Now getNow() {
        return now;
    }
}

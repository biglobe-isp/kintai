package domain;

import domain.ValueForm.Date;
import datasource.Now;

import java.time.LocalDate;

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

    public Boolean isAfter20181115() {
        int year;
        int month;
        int dayOfMonth = 0;

            String date = this.date.getValue();
            year = Integer.parseInt(date.substring(0, 4));
            month = Integer.parseInt(date.substring(4, 6));

            if (date.length() > 6) {
                dayOfMonth = Integer.parseInt(date.substring(6));
            }

            LocalDate dateOfWork = LocalDate.of(year, month, dayOfMonth);
        LocalDate dateOfChange = LocalDate.of(2018, 11, 15);

        return dateOfWork.isAfter(dateOfChange);
    }

}

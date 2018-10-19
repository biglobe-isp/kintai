package domain.ValueForm;

import java.time.LocalDate;

public class Date {

    private final String date;
    private int year;
    private int month;
    private int dayOfMonth;

    public Date(String date) {

        this.date = date;
        this.year = Integer.parseInt(date.substring(0, 4));
        this.month = Integer.parseInt(date.substring(4, 6));

        if (date.length() > 6) {
            this.dayOfMonth = Integer.parseInt(date.substring(6));
        }

    }

    public String getDateVal() {
        return date;
    }


    public Boolean isAfter() {
        LocalDate dateOfWork = LocalDate.of(year, month, dayOfMonth);
        LocalDate dateOfChange = LocalDate.of(2018, 11, 14);

        return dateOfWork.isAfter(dateOfChange);
    }

}

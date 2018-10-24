package refoctor.domain.japan;

import refoctor.domain.japan.Form.NowTime;
import refoctor.domain.japan.Form.Date;

import java.time.LocalDate;

public class DateDomain {
    private final Date date;
    private final NowTime nowTime;

    public DateDomain(Date date, NowTime nowTime) {
        this.date = date;
        this.nowTime = nowTime;
    }

    public Date getDate() {
        return date;
    }

    public NowTime getNowTime() {
        return nowTime;
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

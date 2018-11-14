package domain;

import java.time.LocalDateTime;

public class NowDate {

    public String nowDate;

    // now()はdatasource層？
    public NowDate() {
        this.nowDate = LocalDateTime.now().toString();
    }
}

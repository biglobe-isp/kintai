package domain;

import java.time.LocalDateTime;

public class DateVO {
    private final String date;
    private final String now = LocalDateTime.now().toString();

    public DateVO(String date) {
        this.date = date;
        //this.now = LocalDateTime.now().toString(); //TODO コンストラクタかフィールド、どちらに書くのがより良いだろうか。
    }

    public String getDate() {
        return date;
    }

    public String getNow() {
        return now;
    }
}

package domain;

import java.time.LocalDateTime;

public class DateVO {
    private final String date;
    private final String now = LocalDateTime.now().toString(); //TODO dateVOに含めるより適切な箇所はあるか。

    public DateVO(String date) {
        this.date = date;
        //this.now = LocalDateTime.now().toString(); //TODO コンストラクタかフィールドかgetterのreturn部、どれに書くのがより良いか。
    }

    public String getDate() {
        return date;
    }

    public String getNow() {
        return now;
    }
}

package api;

import java.time.LocalDateTime;

public class TimeGetter {

    private final String now;

    TimeGetter(){
        this.now = LocalDateTime.now().toString();
    }

    public String getNow() {
        return now;
    }
}

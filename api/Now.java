package api;

import java.time.LocalDateTime;

public class Now {

    private final String now;

    public Now() {

        this.now = LocalDateTime.now().toString();
    }

    public String getNowVal() {
        return now;
    }
}

package domain;

import java.time.LocalDateTime;

public class Now {
    private final String now;

    Now(){
        this.now = LocalDateTime.now().toString();
    }

    public String getNow() {
        return now;
    }
}
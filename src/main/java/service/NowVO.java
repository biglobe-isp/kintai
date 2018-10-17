package service;

import java.time.LocalDateTime;

public class NowVO {
    private final String now;

    public NowVO() {
        this.now = LocalDateTime.now().toString();;
    }

    public String getNow() {
        return now;
    }
}

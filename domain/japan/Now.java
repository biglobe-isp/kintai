package domain.japan;

import java.time.LocalDateTime;

public class Now {

    private final String now;

    public Now(LocalDateTime now) {

        this.now = now.toString();
    }

    public String getNowVal() {
        return now;
    }
}

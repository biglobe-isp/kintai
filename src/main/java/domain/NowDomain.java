package domain;

import java.time.LocalDateTime;

public class NowDomain {

    public String now;

    public NowDomain() {
        this.now = LocalDateTime.now().toString();
    }
}

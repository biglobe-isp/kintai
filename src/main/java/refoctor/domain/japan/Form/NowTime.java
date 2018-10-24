package refoctor.domain.japan.Form;

import java.time.LocalDateTime;

public class NowTime {
    private final String now;


    public NowTime(LocalDateTime now) {
        this.now = now.toString();
    }

    public String getValue() {
        return now;
    }
}

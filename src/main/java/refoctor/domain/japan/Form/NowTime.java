package refoctor.domain.japan.Form;

import java.time.LocalDateTime;

public class NowTime {
    private final String now;


    public NowTime() {
        this.now = LocalDateTime.now().toString();
    }

    public String getValue() {
        return now;
    }
}

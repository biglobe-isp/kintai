package refactor.domain;

import java.time.LocalDateTime;

public class NowTime {
    private String nowTime;

    public NowTime() {
        this.nowTime = LocalDateTime.now().toString();
    }

    public String getNowTime() {
        return nowTime;
    }
}

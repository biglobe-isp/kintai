package api;

import domain.TimeGetterRepository;

import java.time.LocalDateTime;

/**
 * 時間取得に関するクラス。
 */
public class TimeGetter implements TimeGetterRepository {

    private final String now;

    TimeGetter(){
        this.now = LocalDateTime.now().toString();
    }

    public String getNow() {
        return now;
    }
}

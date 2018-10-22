package api;

import domain.INowRepository;

import java.time.LocalDateTime;

public class NowRepository implements INowRepository {
    private final String currentTime;

    public NowRepository() {
        this.currentTime = LocalDateTime.now().toString();
    }

    public String getCurrentTime() {
        return currentTime;
    }
}

package kintai.domain.util;

import java.time.LocalDateTime;

public interface Clock {
    public LocalDateTime now();
}
package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Hour {

    @Getter
    private final int value;

    public Hour(int value) {
        if (!this.isCorrectAsHour(value)) throw new RuntimeException("正しい時を設定してください");
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    private boolean isCorrectAsHour(int hour) {
        if (0 <= hour && hour < 24) return true;
        return false;
    }
}

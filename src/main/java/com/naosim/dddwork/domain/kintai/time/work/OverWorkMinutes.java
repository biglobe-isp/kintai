package com.naosim.dddwork.domain.kintai.time.work;

import com.naosim.dddwork.domain.kintai.time.Minute;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class OverWorkMinutes {

    @Getter
    private final Minute minute;

    public OverWorkMinutes(int value) {
        this.minute = new Minute(value);
    }

    public int getInt() {
        return this.minute.getValue();
    }

    @Override
    public String toString() {
        return this.minute.toString();
    }

    public static OverWorkMinutes get(WorkMinutes workMinutes) {
        return new OverWorkMinutes(Math.max(workMinutes.getInt() - 8 * 60, 0));
    }
}

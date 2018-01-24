package com.naosim.dddwork.domain.time.work;

import com.naosim.dddwork.domain.time.Minute;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
}

package domain.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class OverWorkMinutes {
    private final int overWorkMinutes;

    public OverWorkMinutes(int overWorkMinutes) {
        if (overWorkMinutes < 0) {
            throw new IllegalArgumentException("残業時間は0分以上である必要があります。");
        }

        this.overWorkMinutes = overWorkMinutes;
    }

    public int getValue() {
        return this.overWorkMinutes;
    }
}

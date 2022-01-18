package domain.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public final class BreakTime {
    private final int StartHour;
    private final int startMinutes;
    private final int endHour;
    private final int endMinutes;

    private BreakTime(int StartHour, int startMinutes, int endHour, int endMinutes) {
        if (StartHour < 0 || StartHour > 24 || startMinutes < 0 || startMinutes > 59) {
            throw new IllegalArgumentException("不正な休憩開始不正な時刻です。");
        }

        if (StartHour < 9) {
            throw new IllegalArgumentException("休憩開始時刻は9:00以降である必要があります。");
        }

        if (endHour < 0 || endHour > 24 || endMinutes < 0 || endMinutes > 59) {
            throw new IllegalArgumentException("不正な休憩終了時刻です。");
        }

        if (endHour == 24 && endMinutes > 0) {
            throw new IllegalArgumentException("休憩終了時刻は24:00以前である必要があります。");
        }

        if (StartHour > endHour || StartHour == endHour && startMinutes >= endMinutes) {
            throw new IllegalArgumentException("休憩開始時刻は休憩終了時刻より早い必要があります。");
        }

        this.StartHour = StartHour;
        this.startMinutes = startMinutes;
        this.endHour = endHour;
        this.endMinutes = endMinutes;
    }

    public static BreakTime[] getBreakTimes() {
        return new BreakTime[]{
                new BreakTime(12, 0, 13, 0),
                new BreakTime(18, 0, 19, 0),
                new BreakTime(21, 0, 22, 0)
        };
    }
}

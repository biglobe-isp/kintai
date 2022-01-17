package domain.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class WorkTime {
    private static final int MINUTES_PER_HOUR = 60;

    private final int startHour;
    private final int startMinutes;
    private final int endHour;
    private final int endMinutes;

    public WorkTime(int startHour, int startMinutes, int endHour, int endMinutes) {
        if (startHour < 0 || startHour > 24 || startMinutes < 0 || startMinutes > 59) {
            throw new IllegalArgumentException("不正な時刻です。");
        }

        if (startHour == 9 && startMinutes > 0) {
            throw new IllegalArgumentException("開始時刻は9:00以前である必要があります。");
        }

        if (endHour < 0 || endHour > 24 || endMinutes < 0 || endMinutes > 59) {
            throw new IllegalArgumentException("不正な時刻です。");
        }

        if (endHour == 24 && endMinutes > 0) {
            throw new IllegalArgumentException("終了時刻は24:00以前である必要があります。");
        }

        if (startHour > endHour || startHour == endHour && startMinutes > endMinutes) {
            throw new IllegalArgumentException("開始時刻は終了時刻より早いか同じである必要があります。");
        }

        this.startHour = startHour;
        this.startMinutes = startMinutes;
        this.endHour = endHour;
        this.endMinutes = endMinutes;
    }

    public String getStartTimeString() {
        return String.format("%02d%02d", startHour, startMinutes);
    }

    public String getEndTimeString() {
        return String.format("%02d%02d", endHour, endMinutes);
    }

    public WorkMinutes computeWorkMinutes() {
        return new WorkMinutes(computeWorkMinutesInternally());
    }

    public OverWorkMinutes computeOverWorkMinutes() {
        int normalWorkHour = 8;
        int normalWorkMinutes = normalWorkHour * MINUTES_PER_HOUR;
        return new OverWorkMinutes(Math.max(this.computeWorkMinutesInternally() - normalWorkMinutes, 0));
    }

    private int computeWorkMinutesInternally() {
        return computeWholeMinutes() - computeBreakTimeMinutes();
    }

    private int computeWholeMinutes() {
        return (endHour - startHour) * MINUTES_PER_HOUR + endMinutes - startMinutes;
    }

    private int computeBreakTimeMinutes() {
        int breakTimeMinutes = 0;
        if (endHour == 12) {
            breakTimeMinutes += endMinutes;
        } else if (endHour >= 13) {
            breakTimeMinutes += MINUTES_PER_HOUR;
        }

        if (endHour == 18) {
            breakTimeMinutes += endMinutes;
        } else if (endHour >= 19) {
            breakTimeMinutes += MINUTES_PER_HOUR;
        }

        if (endHour == 21) {
            breakTimeMinutes += endMinutes;
        } else if (endHour >= 22) {
            breakTimeMinutes += MINUTES_PER_HOUR;
        }
        return breakTimeMinutes;
    }

}

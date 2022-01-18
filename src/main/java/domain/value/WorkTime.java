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

    public int getWholeMinutes() {
        return computeDifferenceBetween(startHour, startMinutes, endHour, endMinutes);
    }

    public static WorkTime createDayOffWorkTime() {
        return new WorkTime(9, 0, 18, 0);
    }

    public static WorkTime createMorningOffWorkTime(int endHour, int endMinutes) {
        if (endHour < 12) {
            throw new IllegalArgumentException("午前休をとる場合勤務終了は１２時以降である必要があります。");
        }
        return new WorkTime(9, 0, endHour, endMinutes);
    }

    public static WorkTime createAfternoonOffWorkTime(int startHour, int startMinutes) {
        return new WorkTime(startHour, startMinutes, 18, 0);
    }

    public int computeEachBreakMinutes(BreakTime breakTime) {
        final int breakTimeStartHour = breakTime.getStartHour();
        final int breakTimeStartMinutes = breakTime.getStartMinutes();
        return Math.min(
                Math.max(computeDifferenceBetween(breakTimeStartHour, breakTimeStartMinutes, endHour, endMinutes), 0),
                computeDifferenceBetween(breakTimeStartHour, breakTimeStartMinutes, breakTime.getEndHour(), breakTime.getEndMinutes())
        );
    }

    private int computeDifferenceBetween(int fromHour, int fromMinutes, int toHour, int toMinutes) {
        return (toHour - fromHour) * MINUTES_PER_HOUR + toMinutes - fromMinutes;
    }
}

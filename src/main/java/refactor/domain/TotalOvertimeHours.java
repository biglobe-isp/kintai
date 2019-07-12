package refactor.domain;

public class TotalOvertimeHours {
    private final int totalOvertimeHoursInMinutes;

    public TotalOvertimeHours(int totalOvertimeHoursInMinutes) {
        this.totalOvertimeHoursInMinutes = totalOvertimeHoursInMinutes;
    }

    @Override
    public String toString() {
        return String.format("%d時間%d分", totalOvertimeHoursInMinutes / 60, totalOvertimeHoursInMinutes % 60);
    }
}

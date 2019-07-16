package refactor.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TotalOvertimeHours {
    private final int totalOvertimeHoursInMinutes;

    @Override
    public String toString() {
        return String.format("%d時間%d分", totalOvertimeHoursInMinutes / 60, totalOvertimeHoursInMinutes % 60);
    }
}

package refactor.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TotalActualWorkingHours {
    private final int totalWorkingHoursInMinutes;

    @Override
    public String toString() {
        return String.format("%d時間%d分", totalWorkingHoursInMinutes / 60, totalWorkingHoursInMinutes % 60);
    }
}

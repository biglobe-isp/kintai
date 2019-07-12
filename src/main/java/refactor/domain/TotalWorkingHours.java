package refactor.domain;

public class TotalWorkingHours {
    private final int totalWorkingHoursInMinutes;

    public TotalWorkingHours(int totalWorkingHoursInMinutes) {
        this.totalWorkingHoursInMinutes = totalWorkingHoursInMinutes;
    }

    @Override
    public String toString() {
        return String.format("%d時間%d分", totalWorkingHoursInMinutes / 60, totalWorkingHoursInMinutes % 60);
    }
}

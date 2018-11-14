package refactor.domain.dto.Item;

public class WorkAndOverWorkMinutesItem {
    private final int workMinutes;
    private final int overWorkMinutes;

    public WorkAndOverWorkMinutesItem(int workMinutes, int overWorkMinutes) {
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }
}

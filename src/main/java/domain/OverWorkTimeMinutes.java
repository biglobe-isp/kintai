package domain;

public class OverWorkTimeMinutes {

    public int overWorkMinutes;

    public OverWorkTimeMinutes(WorkTimeMinutes workTimeMinutes) {
        this.overWorkMinutes = Math.max(workTimeMinutes.workTimeMinutes - 8 * 60, 0);
    }

}

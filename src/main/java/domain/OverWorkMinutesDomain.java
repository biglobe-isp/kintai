package domain;

public class OverWorkMinutesDomain {

    public int overWorkMinutes;

    public OverWorkMinutesDomain(WorkMinutesDomain workMinutesDomain) {
        this.overWorkMinutes = Math.max(workMinutesDomain.workMinutes - 8 * 60, 0);
    }
}

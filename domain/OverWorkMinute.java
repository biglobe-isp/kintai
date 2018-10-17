package domain;

public class OverWorkMinute {
    private final int overWorkMinute;

    OverWorkMinute(int workMinute) {
        this.overWorkMinute = Math.max(workMinute - 8 * 60, 0);
    }

    public int getOverWorkMinute() {
        return overWorkMinute;
    }
}
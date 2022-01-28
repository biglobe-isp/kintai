package src.com.naosim.dddwork.domain;

public class OverTime {
    private final int overTime;

    public OverTime(WorkTime workTime) {
        this.overTime = Math.max(workTime.getValue() - 8 * 60, 0);
    }

    public OverTime(int value) {
        this.overTime = value;
    }
    public int getValue() {
        return this.overTime;
    }

}

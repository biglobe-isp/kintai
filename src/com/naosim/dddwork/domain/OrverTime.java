package src.com.naosim.dddwork.domain;

public class OrverTime {
    private final WorkTime workTime;

    public OrverTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public int calcOverTime() {
        return Math.max(workTime.calcWorkTime() - 8 * 60, 0);
    }
}

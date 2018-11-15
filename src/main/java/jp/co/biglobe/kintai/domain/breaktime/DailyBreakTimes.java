package jp.co.biglobe.kintai.domain.breaktime;

import java.util.ArrayList;
import java.util.List;

public class DailyBreakTimes {

    private List<BreakTime> breakTimes;

    public DailyBreakTimes() {
        this.breakTimes = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.breakTimes.isEmpty();
    }

    public void add(BreakTime breakTime) {
        this.breakTimes.add(breakTime);
    }

    public List<BreakTime> getBreakTimes() {
        return this.breakTimes;
    }
}

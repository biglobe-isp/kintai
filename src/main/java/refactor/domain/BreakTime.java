package refactor.domain;

import java.util.Objects;

public class BreakTime {
    private final EndTime endTime;

    public BreakTime(EndTime endTime) {
        this.endTime = Objects.requireNonNull(endTime);
    }

    public int inMinutes() {
        int breakTime = 0;
        int endH = endTime.getHour();

        if (endH == 12) {
            breakTime += endTime.getMinute();
        } else if (endH >= 13) {
            breakTime += 60;
        }

        if (endH == 18) {
            breakTime += endTime.getMinute();
        } else if (endH >= 19) {
            breakTime += 60;
        }

        if (endH == 21) {
            breakTime += endTime.getMinute();
        } else if (endH >= 22) {
            breakTime += 60;
        }

        return breakTime;
    }
}

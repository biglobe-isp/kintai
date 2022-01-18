package domain.entity;

import domain.value.*;
import lombok.Getter;
import lombok.NonNull;

@Getter
public final class WorkRecord {
    private static final int MINUTES_PER_HOUR = 60;

    private WorkRecordId workRecordId;
    private WorkDate workDate;
    private WorkTime workTime;

    public WorkRecord(@NonNull WorkDate workDate, @NonNull WorkTime workTime) {
        this.workDate = workDate;
        this.workTime = workTime;
    }

    public String getWorkDateString() {
        return this.workDate.toString();
    }

    public String getStartTimeString() {
        return this.workTime.getStartTimeString();
    }

    public String getEndTimeString() {
        return this.workTime.getEndTimeString();
    }

    public WorkMinutes computeWorkMinutes() {
        return new WorkMinutes(computeWorkMinutesInternally());
    }

    public OverWorkMinutes computeOverWorkMinutes() {
        return new OverWorkMinutes(Math.max(computeWorkMinutesInternally() - 480, 0));
    }

    private int computeWorkMinutesInternally() {
        int workMinutes = this.workTime.getWholeMinutes();
        for (BreakTime breakTime: BreakTime.getBreakTimes()) {
            workMinutes -= this.workTime.computeEachBreakMinutes(breakTime);
        }
        return workMinutes;
    }
}

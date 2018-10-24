package refoctor.domain.japan;

public class WorkTime {
    private final int workTime;
    private final int overWork;

    public WorkTime(StartTime startTime, EndTime endTime, DateDomain dateDomain) {

        int totalWorkMinutes = endTime.getEndTotalMinutes() - startTime.getStartTotalMinutes();

        if (endTime.getEndHour().getValue() == 12) {
            totalWorkMinutes -= endTime.getEndMinutes().getValue();
        } else if (endTime.getEndHour().getValue() >= 13) {
            totalWorkMinutes -= 60;
        }

        if (endTime.getEndHour().getValue() == 18) {
            totalWorkMinutes -= endTime.getEndMinutes().getValue();
        } else if (endTime.getEndHour().getValue() >= 19) {
            totalWorkMinutes -= 60;
        }

        if (endTime.getEndHour().getValue() == 21) {
            totalWorkMinutes -= endTime.getEndMinutes().getValue();
        } else if (endTime.getEndHour().getValue() >= 22) {
            totalWorkMinutes -= 60;
        }

        this.workTime = totalWorkMinutes;

        this.overWork = Math.max(this.workTime - 8 * 60, 0);
    }

    public int getWorkTime() {
        return workTime;
    }

    public int getOverWork() {
        return overWork;
    }
}

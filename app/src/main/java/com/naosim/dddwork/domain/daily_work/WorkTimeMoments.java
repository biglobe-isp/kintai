package com.naosim.dddwork.domain.daily_work;

/**
 * 勤務時刻
 */
public class WorkTimeMoments {
    private final ClockTime startWorkTime;
    private final ClockTime endWorkTime;

    public WorkTimeMoments(ClockTime startWorkTime, ClockTime endWorkTime) {
        this.startWorkTime = new ClockTime(startWorkTime);
        validateStartWorkTime();

        this.endWorkTime = new ClockTime(endWorkTime);
        validateEndWorkTime();
    }

    private void validateStartWorkTime() {
        if (validateTimeMomentsDifference(new ClockTimesDuration(startWorkTime, new ScheduledWorkTimes().getScheduledStartTime()))) throw new IllegalArgumentException("勤務開始時間が9時00分を超えています。遅刻厳禁。");
    }

    private void validateEndWorkTime() {
        if (validateTimeMomentsDifference(new ClockTimesDuration(startWorkTime, endWorkTime))) throw new IllegalArgumentException("勤務終了時刻が勤務開始時刻よりも前に設定されています");
    }

    private boolean validateTimeMomentsDifference(ClockTimesDuration difference) {
        return difference.getTimeMomentsDifference().toMinutes() < 0L;
    }

    ClockTime getStartWorkTime() {
        return new ClockTime(startWorkTime);
    }

    ClockTime getEndWorkTime() {
        return new ClockTime(endWorkTime);
    }
}
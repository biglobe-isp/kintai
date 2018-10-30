package jp.co.biglobe.kintai.domain;

import jp.co.biglobe.kintai.domain.breaktime.BreakTime;
import jp.co.biglobe.kintai.domain.breaktime.DailyBreakTimes;

public class WorkingRule {

    private static WorkingRule INSTANCE;

    private WorkingRule() {
    }

    public static WorkingRule getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorkingRule();
        }
        return INSTANCE;
    }

    public WorkTime calculateWorkTime(WorkTime workTime, DailyBreakTimes breakTimes) {
        WorkTimeBuilder builder = new WorkTimeBuilder();

        int startH = workTime.getStartTime().getHoursAsInt();
        int startM = workTime.getStartTime().getMinutesAsInt();

        int endH = workTime.getEndTime().getHoursAsInt();
        int endM = workTime.getEndTime().getMinutesAsInt();
        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        for (BreakTime breakTime : breakTimes.getBreakTimes()) {
            if (endH == breakTime.getStartBreakTime().getHours()) {
                workMinutes -= endM;
            } else if (endH >= breakTime.getEndBreakTime().getHours()) {
                workMinutes -= 60;
            }
        }

        return builder.build(workTime, workMinutes, Math.max(workMinutes - 8 * 60, 0));
    }
}

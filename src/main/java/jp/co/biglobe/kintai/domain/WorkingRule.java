package jp.co.biglobe.kintai.domain;

import jp.co.biglobe.kintai.datasource.BreakTimeRepositoryDb;
import jp.co.biglobe.kintai.domain.breaktime.BreakTime;
import jp.co.biglobe.kintai.domain.breaktime.DailyBreakTimes;

import java.util.List;

public class WorkingRule {

    private static final int coolTimes[][] = {{12, 13}, {18, 19}, {21, 22}};
    private static final int coolTimeIndex_StartTime = 0;
    private static final int coolTimeIndex_EntTime = 1;

    private final BreakTimeRepository breakTimeRepository;

    private static WorkingRule INSTANCE;

    private WorkingRule() {
        this.breakTimeRepository = new BreakTimeRepositoryDb();
    }

    public static WorkingRule getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorkingRule();
        }
        return INSTANCE;
    }

    public WorkTime calculateWorkTime(WorkTime workTime) {
        WorkTimeBuilder builder = new WorkTimeBuilder();

        int startH = workTime.getStartTime().getHoursAsInt();
        int startM = workTime.getStartTime().getMinutesAsInt();

        int endH = workTime.getEndTime().getHoursAsInt();
        int endM = workTime.getEndTime().getMinutesAsInt();
        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        DailyBreakTimes breakTimes = this.breakTimeRepository.findBreakTimes();

        for (BreakTime breakTime : breakTimes.getBreakTimes()) {
            if (endH == breakTime.getStartBreakTime().getHours()) {
                workMinutes -= endM;
            } else if (endH >= breakTime.getEndBreakTime().getHours()) {
                workMinutes -= 60;
            }
        }

        return builder.buildWorkTime(workTime, workMinutes, Math.max(workMinutes - 8 * 60, 0));
    }
}

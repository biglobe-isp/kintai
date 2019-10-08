package com.naosim.dddwork.domain;

import java.util.List;

/***
 * 年日別ごとの労働時間を表す値オブジェクト
 */
public class MonthlyTotalWorkTime {
    private WorkTimeDuration totalWorkTime;
    private WorkTimeDuration totalOverWork;

    public MonthlyTotalWorkTime(List<KintaiData> list) {
        int total = 0;
        int over = 0;

        for (KintaiData k : list) {
            total += Integer.valueOf(k.getWorkMinutes());
            over += Integer.valueOf(k.getOverWorkMinutes());
        }
        this.totalWorkTime = new WorkTimeDuration(total);
        this.totalOverWork = new WorkTimeDuration(over);
    }

    public int getWorkTimeHour() {
        return this.totalWorkTime.getHour();
    }

    public int getWorkTimeMinute() {
        return this.totalWorkTime.getMinutes();
    }

    public int getOverWorkTimeHour() {
        return this.totalOverWork.getHour();
    }

    public int getOverTimeMinute() {
        return this.totalOverWork.getMinutes();
    }

}

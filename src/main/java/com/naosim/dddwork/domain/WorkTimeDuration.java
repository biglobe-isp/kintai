package com.naosim.dddwork.domain;

/***
 * 勤務時間を表す値オブジェクト
 */
public class WorkTimeDuration {
    private int minutes = 0;

    public WorkTimeDuration(int minutes) {
        this.minutes = minutes;
    }

    public int getWorkTimeMinutes() {
        return this.minutes;
    }

    /***
     * 勤務時間（時）を返す。
     * 切り捨て
     * @return
     */
    public int getHour() {
        return minutes / 60;
    }

    /***
     * 勤務時間（分）を返す。
     * 切り捨て
     * @return
     */

    public int getMinutes() {
        return minutes % 60;
    }
}

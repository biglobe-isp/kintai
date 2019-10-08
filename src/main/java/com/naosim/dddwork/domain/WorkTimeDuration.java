package com.naosim.dddwork.domain;

import java.time.Duration;

/***
 * 勤務時間を表す値オブジェクト
 */
public class WorkTimeDuration {
    private Duration duration;

    public WorkTimeDuration(long minutes) {

        this.duration = Duration.ofMinutes(minutes);
    }

    public long getWorkTimeMinutes() {
        return this.duration.toMinutes();
    }

    /***
     * 勤務時間（時）を返す。
     * 切り捨て
     * @return
     */
    public long getHour() {
        return this.duration.toHours();
    }

    /***
     * 勤務時間（分）を返す。
     * 切り捨て
     * @return
     */

    public long getMinutes() {
        return this.duration.toMinutes() % 60;
    }
}

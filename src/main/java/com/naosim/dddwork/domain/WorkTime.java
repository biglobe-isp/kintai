package com.naosim.dddwork.domain;

/***
 * 勤務の時刻を表す値オブジェクト
 */
public class WorkTime {
    private String time;

    public WorkTime(String time) {
        this.time = time;
    }

    public int getHour() {
        return Integer.valueOf(this.time.substring(0, 2));
    }

    public int getMinute() {
        return Integer.valueOf(this.time.substring(2, 4));
    }

    @Override
    public String toString() {
        return time;
    }
}

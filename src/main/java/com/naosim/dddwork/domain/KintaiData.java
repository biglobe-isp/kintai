package com.naosim.dddwork.domain;

import com.naosim.dddwork.datasouce.IKintai;

import java.time.Duration;

/**
 * 日毎の勤務を表すオブジェクト
 */
public class KintaiData implements IKintai {
    //勤務日
    private WorkDate date;
    //開始時刻
    private WorkTime start;
    //終了時刻
    private WorkTime end;
    //労働時間
    private WorkTimeDuration workTime;
    //残業時間
    private WorkTimeDuration overWorkTime;


    public KintaiData(String date, String start, String end) {
        this.date = new WorkDate(date);
        this.start = new WorkTime(start);
        this.end = new WorkTime(end);
        this.workTime = new WorkTimeDuration(this.calculateJiturodo());
        this.overWorkTime = new WorkTimeDuration(this.calculateZangyo());

    }

    private long calculateJiturodo() {
        long workMinutes = Duration.between(this.start.getLocalTIme(), this.end.getLocalTIme()).toMinutes();
        RestTimeService service = new RestTimeService();
        long totalRestTime = service.calculateRestTime(this.start, this.end);
        return workMinutes - totalRestTime;

    }

    private long calculateZangyo() {
        long workMinutes = calculateJiturodo();
        return Math.max(workMinutes - 8 * 60, 0);

    }

    @Override
    public String getWorkMinutes() {
        return String.valueOf(this.workTime.getWorkTimeMinutes());
    }

    @Override
    public String getOverWorkMinutes() {
        return String.valueOf(this.overWorkTime.getWorkTimeMinutes());
    }

    @Override
    public String getDate() {
        return this.date.toString();
    }

    @Override
    public String getStart() {
        return this.start.toString();
    }

    @Override
    public String getEnd() {
        return this.end.toString();
    }
}

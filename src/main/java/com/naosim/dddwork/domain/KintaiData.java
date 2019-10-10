package com.naosim.dddwork.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 日毎の勤務を表すオブジェクト
 */
public class KintaiData {
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
    

    public KintaiData(LocalDate date, LocalTime start, LocalTime end) {
        this.date = WorkDate.from(date);
        this.start = WorkTime.from(start);
        this.end = WorkTime.from(end);
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


    public String getWorkMinutes() {
        return String.valueOf(this.workTime.getWorkTimeMinutes());
    }

    public String getOverWorkMinutes() {
        return String.valueOf(this.overWorkTime.getWorkTimeMinutes());
    }

    public String getDate() {
        return this.date.toString();
    }

    public String getStart() {
        return this.start.toString();
    }

    public String getEnd() {
        return this.end.toString();
    }
}

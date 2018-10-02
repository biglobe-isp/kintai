package jp.co.biglobe.kintai.domain;

public class WorkTime {


    //セッターを除去してコンストラクタへ

    private WorkDate date;

    private StartWorkTime startTime;
    private EndWorkTime endTime;

    private int minutes;

    private int overWorkMinutes;

    private String now;


    public WorkDate getDate() {
        return date;
    }

    public void setDate(WorkDate date) {
        this.date = date;
    }

    public StartWorkTime getStartTime() {
        return startTime;
    }

    public void setStartTime(StartWorkTime start) {
        this.startTime = start;
    }

    public EndWorkTime getEndTime() {
        return endTime;
    }

    public void setEndTime(EndWorkTime end) {
        this.endTime = end;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }

    public void setOverWorkMinutes(int overWorkMinutes) {
        this.overWorkMinutes = overWorkMinutes;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }



}

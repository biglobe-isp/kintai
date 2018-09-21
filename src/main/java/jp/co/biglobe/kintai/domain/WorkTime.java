package jp.co.biglobe.kintai.domain;

public class WorkTime {

    private WorkDate date;

    private StartWorkTime start;
    private EndWorkTime end;

    private int minutes;

    private int overWorkMinutes;

    private String now;


    public WorkDate getDate() {
        return date;
    }

    public void setDate(WorkDate date) {
        this.date = date;
    }

    public StartWorkTime getStart() {
        return start;
    }

    public void setStart(StartWorkTime start) {
        this.start = start;
    }

    public EndWorkTime getEnd() {
        return end;
    }

    public void setEnd(EndWorkTime end) {
        this.end = end;
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

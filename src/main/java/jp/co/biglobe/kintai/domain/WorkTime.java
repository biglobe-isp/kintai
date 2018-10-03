package jp.co.biglobe.kintai.domain;

public class WorkTime {

    private WorkDate date;

    private StartWorkTime startTime;
    private EndWorkTime endTime;

    private int minutes;
    private int overWorkMinutes;

    private NowTime now;

    public WorkTime(){}

    public WorkTime(
            int minutes,
            int overMinutes
    ){
        this.minutes = minutes;
        this.overWorkMinutes = overMinutes;
    }

    public WorkTime(
            WorkDate date,
            StartWorkTime startTime,
            EndWorkTime endTime,
            int minutes,
            int overWorkMinutes,
            NowTime nowTime
    ){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minutes = minutes;
        this.overWorkMinutes = overWorkMinutes;
        this.now = nowTime;
    }

    public WorkDate getDate() {
        return date;
    }

    public StartWorkTime getStartTime() {
        return startTime;
    }

    public EndWorkTime getEndTime() {
        return endTime;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }

    public NowTime getNow() {
        return now;
    }

}

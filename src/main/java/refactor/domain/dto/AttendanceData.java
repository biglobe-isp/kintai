package refactor.datasource.entity;

public class AttendanceData {
    private String date;
    private String startTime;
    private String endTime;
    private int workMinutes;
    private int overWorkMinutes;
    private String nowTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public void setWorkMinutes(int workMinutes) {
        this.workMinutes = workMinutes;
    }

    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }

    public void setOverWorkMinutes(int overWorkMinutes) {
        this.overWorkMinutes = overWorkMinutes;
    }
    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

}

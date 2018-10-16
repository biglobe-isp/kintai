package domain;

import java.time.LocalDateTime;

public class DateAndTimeRepository {
    private int startM;
    private int startH;
    private int endH;
    private int endM;
    private String date;
    private int workMinutes;
    private int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
    private String now = LocalDateTime.now().toString();

    public DateAndTimeRepository(String start, String end, String date) {
        this.startH = Integer.valueOf(start.substring(0, 2));
        this.startM = Integer.valueOf(start.substring(2, 4));
        this.endH = Integer.valueOf(end.substring(0, 2));
        this.endM = Integer.valueOf(end.substring(2, 4));
        this.date = date;
        this.workMinutes = this.endH * 60 + this.endM - (this.startH * 60 + this.startM);
    }

    public int getStartM() {
        return startM;
    }

    public void setStartM(int startM) {
        this.startM = startM;
    }

    public int getStartH() {
        return startH;
    }

    public void setStartH(int startH) {
        this.startH = startH;
    }

    public int getEndH() {
        return endH;
    }

    public void setEndH(int endH) {
        this.endH = endH;
    }

    public int getEndM() {
        return endM;
    }

    public void setEndM(int endM) {
        this.endM = endM;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }

    public void setOverWorkMinutes(int overWorkMinutes) {
        this.overWorkMinutes = overWorkMinutes;
    }

    public int getWorkMinutes() {
        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }

        return workMinutes;
    }

    public void setWorkMinutes(int workMinutes) {
        this.workMinutes = workMinutes;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }
}

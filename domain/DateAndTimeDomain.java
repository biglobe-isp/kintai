package domain;

import java.time.LocalDateTime;

public class DateAndTimeDomain {

    private final String methodType;
    private final String date;
    private final String now = LocalDateTime.now().toString();
    private final String start;
    private final String end;
    private final int startH;
    private final int startM;
    private final int endH;
    private final int endM;
    private final int workMinutes;
    private final int overWorkMinutes;
    private int tmpWorkMinutes;

    public DateAndTimeDomain(String methodType, String date, String start, String end) {
        this.methodType = methodType;
        this.date = date;
        this.start = start;
        this.end = end;
        this.startH = Integer.valueOf(this.start.substring(0, 2));
        this.startM = Integer.valueOf(this.start.substring(2, 4));
        this.endH = Integer.valueOf(this.end.substring(0, 2));
        this.endM = Integer.valueOf(this.end.substring(2, 4));
        this.tmpWorkMinutes = this.endH * 60 + this.endM - (this.startH * 60 + this.startM);
        if (endH == 12) {
            this.tmpWorkMinutes -= endM;
        } else if (endH >= 13) {
            this.tmpWorkMinutes -= 60;
        } else if  (endH == 18) {
            this.tmpWorkMinutes -= endM;
        } else if (endH >= 19) {
            this.tmpWorkMinutes -= 60;
        } else if  (endH == 21) {
            this.tmpWorkMinutes -= endM;
        } else if (endH >= 22) {
            this.tmpWorkMinutes -= 60;
        }
        this.workMinutes = this.tmpWorkMinutes;
        this.overWorkMinutes = Math.max(getWorkMinutes() - 8 * 60, 0);
    }


    public DateAndTimeDomain(String methodType, String date) {
        this.methodType = methodType;
        this.date = date;
        this.start = null;
        this.end = null;
        this.startH = 0;
        this.startM = 0;
        this.endH = 0;
        this.endM = 0;
        this.workMinutes = 0;
        this.overWorkMinutes = 0;
    }



    public String getMethodType() {
        return methodType;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getStartM() {
        return startM;
    }

    public int getStartH() {
        return startH;
    }

    public int getEndH() {
        return endH;
    }

    public int getEndM() {
        return endM;
    }

    public String getDate() {
        return date;
    }

    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public String getNow() {
        return now;
    }
}

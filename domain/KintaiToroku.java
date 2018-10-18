package domain;

public class KintaiToroku {

    private String date;
    private String startDate;
    private String endDate;


    public KintaiToroku(String date, String startDate, String endDate) {
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDate() {
        Date date = new Date(this.date);
        return date.getDate();
    }

    public String getNow() {
        Now now = new Now();
        return now.getNow();
    }

    public int getStartHourInt() {
        StartHour startHour = new StartHour(this.startDate);
        return startHour.getStartHourInt();
    }

    public int getStartMinuteInt() {
        StartMinute startMinute = new StartMinute(this.startDate);
        return startMinute.getStartMinuteInt();
    }

    public int getEndHourInt() {
        EndHour endHour = new EndHour(this.endDate);
        return endHour.getEndHourInt();
    }

    public int getEndMinuteInt() {
        EndMinute endMinute = new EndMinute(this.endDate);
        return endMinute.getEndMinuteInt();
    }
    public String getStartHourStr() {
        StartHour startHour = new StartHour(this.startDate);
        return startHour.getStartHourStr();
    }

    public String getStartMinuteStr() {
        StartMinute startMinute = new StartMinute(this.startDate);
        return startMinute.getStartMinuteStr();
    }

    public String getEndHourStr() {
        EndHour endHour = new EndHour(this.endDate);
        return endHour.getEndHourStr();
    }

    public String getEndMinuteStr() {
        EndMinute endMinute = new EndMinute(this.endDate);
        return endMinute.getEndMinuteStr();
    }

    public int getWorkMinute() {
        WorkMinute workMinute = new WorkMinute(getStartHourInt(), getStartMinuteInt(), getEndHourInt(), getEndMinuteInt());
        return workMinute.getWorkMinute();
    }

    public int getOverWorkMinute() {
        OverWorkMinute overWorkMinute = new OverWorkMinute(getWorkMinute());
        return overWorkMinute.getOverWorkMinute();
    }

}

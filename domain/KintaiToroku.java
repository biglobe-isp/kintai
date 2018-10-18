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

    public int getStartHour() {
        StartHour startHour = new StartHour(this.startDate);
        return startHour.getStartHour();
    }

    public int getStartMinute() {
        StartMinute startMinute = new StartMinute(this.startDate);
        return startMinute.getStartMinute();
    }

    public int getEndHour() {
        EndHour endHour = new EndHour(this.endDate);
        return endHour.getEndHour();
    }

    public int getEndMinute() {
        EndMinute endMinute = new EndMinute(this.endDate);
        return endMinute.getEndMinute();
    }

    public int getWorkMinute() {
        WorkMinute workMinute = new WorkMinute(getStartHour(), getStartMinute(), getEndHour(), getEndMinute());
        return workMinute.getWorkMinute();
    }

    public int getOverWorkMinute() {
        OverWorkMinute overWorkMinute = new OverWorkMinute(getWorkMinute());
        return overWorkMinute.getOverWorkMinute();
    }

}

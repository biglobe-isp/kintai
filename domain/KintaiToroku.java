package domain;

public class KintaiToroku {

    private Date date;
    private StartDate startDate;
    private EndDate endDate;
    private Now now;
    private StartHour startHour;
    private StartMinute startMinute;
    private EndHour endHour;
    private EndMinute endMinute;
    private WorkMinute workMinute;
    private OverWorkMinute overWorkMinute;

    public KintaiToroku(String date, String startDate, String endDate){

        this.date = new Date(date);
        this.startDate = new StartDate(startDate);
        this.endDate = new EndDate(endDate);
        this.now = new Now();
        this.startHour = new StartHour(startDate);
        this.startMinute = new StartMinute(startDate);
        this.endHour = new EndHour(endDate);
        this.endMinute = new EndMinute(endDate);
        this.workMinute = new WorkMinute(startHour.getStartHour(), startMinute.getStartMinute(),
                                            endHour.getEndHour(), endMinute.getEndMinute());
        this.overWorkMinute = new OverWorkMinute(workMinute.getWorkMinute());

    }

    public Date getDate() {
        return date;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public Now getNow() {
        return now;
    }

    public StartHour getStartHour() {
        return startHour;
    }

    public StartMinute getStartMinute() {
        return startMinute;
    }

    public EndHour getEndHour() {
        return endHour;
    }

    public EndMinute getEndMinute() {
        return endMinute;
    }

    public WorkMinute getWorkMinute() {
        return workMinute;
    }

    public OverWorkMinute getOverWorkMinute() {
        return overWorkMinute;
    }

}

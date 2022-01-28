package src.com.naosim.dddwork.domain;

public class Regulation {
    private WorkingDate workingDate;
    private StartTime startTime ;
    private EndTime endTime;
    private WorkTime workTime;
    private OverTime overTime;

    public Regulation(WorkingDate date, StartTime start, EndTime end, WorkTime work, OverTime orver) {
        this.workingDate = date;
        this.startTime = start;
        this.endTime = end;
        this.workTime = work;
        this.overTime = orver;
    }
    public WorkingDate getWorkingDate() {return this.workingDate;}

    public StartTime getStart() {return this.startTime;}

    public EndTime getEnd() {return this.endTime;}

    public WorkTime getWorkTime() {return this.workTime;}

    public OverTime getOrverTime() {return this.overTime;}

}

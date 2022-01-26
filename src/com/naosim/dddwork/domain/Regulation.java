package src.com.naosim.dddwork.domain;

public class Regulation {
    private WorkingDate workingDate;
    private StartTime startTime ;
    private EndTime endTime;
    private WorkTime workTime;
    private OrverTime orverTime;

    public Regulation(WorkingDate date, StartTime start, EndTime end, WorkTime work, OrverTime orver) {
        this.workingDate = date;
        this.startTime = start;
        this.endTime = end;
        this.workTime = work;
        this.orverTime = orver;
    }

    public WorkingDate getWorkingDate() {return this.workingDate;}

    public StartTime getStart() {return this.startTime;}

    public EndTime getEnd() {return this.endTime;}

    public WorkTime getWorkTime() {return this.workTime;}

    public OrverTime getOrverTime() {return this.orverTime;}

}

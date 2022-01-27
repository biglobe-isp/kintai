package src.com.naosim.dddwork.domain;

public class WorkTime {
    private final int workTime;
    private StartTime startTime;
    private EndTime endtime;

    private int endHour;
    private int endMinute;
    private int startHour;
    private int startMinute;

    public WorkTime(StartTime start, EndTime end) {
        this.startTime = start;
        this.endtime = end;
        this.endHour = endtime.getEndH();
        this.endMinute = endtime.getEndM();
        this.startHour = startTime.getStartH();
        this.startMinute = startTime.getStartM();

        int workMinutes = this.endHour * 60 + this.endMinute - (this.startHour * 60 + this.startMinute);

        if (this.endHour == 12) {
            workMinutes -= this.endMinute;
        } else if (endHour >= 13) {
            workMinutes -= 60;
        }

        if (endHour == 18) {
            workMinutes -= this.endMinute;
        } else if (endHour >= 19) {
            workMinutes -= 60;
        }

        if (endHour == 21) {
            workMinutes -= this.endMinute;
        } else if (endHour >= 22) {
            workMinutes -= 60;
        }

        this.workTime = workMinutes ;
    }

    public WorkTime(int value) {
        this.workTime = value;
    }

    public int getValue() {
        return this.workTime;
    }

}

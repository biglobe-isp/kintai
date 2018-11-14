package domain;

public class EndTimeHour {

    public int endTimeHour;

    public EndTimeHour(EndTime endTime) {
        this.endTimeHour = Integer.valueOf(endTime.toString().substring(0, 2));
    }
}

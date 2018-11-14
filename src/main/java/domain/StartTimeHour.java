package domain;

public class StartTimeHour {

    public int startTimeHour;

    public StartTimeHour(StartTime startTime) {
        this.startTimeHour = Integer.valueOf(startTime.toString().substring(0, 2));
    }
}

package domain;

public class StartTimeMinute {

    public int startTimeMinute;

    public StartTimeMinute(StartTime startTime){
        this.startTimeMinute = Integer.valueOf(startTime.toString().substring(2, 4));
    }
}

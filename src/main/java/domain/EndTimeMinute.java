package domain;

public class EndTimeMinute {

    public int endTimeMinute;

    public EndTimeMinute(EndTime endTime){
        this.endTimeMinute = Integer.valueOf(endTime.toString().substring(2, 4));
    }
}

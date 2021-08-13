package jp.co.esumit.kintai.domain.field;

public class StartTime {
    private final StartHour startHr;
    private final StartMinute startMin;

    public StartTime(String startTime){
        this.startHr = new StartHour(startTime);
        this.startMin = new StartMinute(startTime);
    }

    public StartHour getStartHr(){
        return this.startHr;
    }

    public StartMinute getStartMin(){
        return this.startMin;
    }

}

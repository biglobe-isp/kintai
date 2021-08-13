package jp.co.esumit.kintai.domain.field;

public class EndTime {
    public final EndHour endHr;
    public final EndMinute endMin;

    public EndTime(String endTime){
        this.endHr = new EndHour(endTime);
        this.endMin = new EndMinute(endTime);
    }

    public EndHour getEndHr(){
        return this.endHr;
    }

    public EndMinute getEndMin(){
        return this.endMin;
    }
}

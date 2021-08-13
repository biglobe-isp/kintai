package jp.co.esumit.kintai.domain.field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartTime {
    private final StartHour startHr;
    private final StartMinute startMin;

    public StartTime(String startTime){

        this.valid(startTime);

        this.startHr = new StartHour(startTime);
        this.startMin = new StartMinute(startTime);
    }

    public StartHour getStartHr(){
        return this.startHr;
    }

    public StartMinute getStartMin(){
        return this.startMin;
    }

    private void valid(String startTime){
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(startTime);
        if(!m.matches()){
            throw new IllegalArgumentException("開始時刻が不正値です。");
        }
    }
}

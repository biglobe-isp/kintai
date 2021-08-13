package jp.co.esumit.kintai.domain.field;

public class StartMinute {
    private final int value;

    public StartMinute(String startTime){
        this.value = Integer.parseInt(startTime.substring(2));
    }

    public int getValue(){
        return this.value;
    }
}

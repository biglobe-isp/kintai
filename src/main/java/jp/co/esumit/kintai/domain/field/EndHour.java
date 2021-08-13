package jp.co.esumit.kintai.domain.field;

public class EndHour {
    private final int value;

    public EndHour(String endTime){
        this.value = Integer.parseInt(endTime.substring(0, 2));
    }

    public int getValue(){
        return this.value;
    }
}

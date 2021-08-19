package jp.co.esumit.kintai.domain.kintai_info.end_time;

public class EndMinute {
    private final int value;

    public EndMinute(String endTime) {
        this.value = Integer.parseInt(endTime.substring(2));
    }

    public int getValue() {
        return this.value;
    }
}

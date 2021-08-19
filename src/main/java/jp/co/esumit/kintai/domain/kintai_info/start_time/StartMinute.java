package jp.co.esumit.kintai.domain.kintai_info.start_time;

public class StartMinute {
    private final int value;

    public StartMinute(String startTime) {
        this.value = Integer.parseInt(startTime.substring(2));
    }

    public int getValue() {
        return this.value;
    }
}

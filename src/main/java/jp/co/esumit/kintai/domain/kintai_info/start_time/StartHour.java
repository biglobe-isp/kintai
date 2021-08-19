package jp.co.esumit.kintai.domain.kintai_info.start_time;

public class StartHour {
    private final int value;

    public StartHour(String startTime) {
        this.value = Integer.parseInt(startTime.substring(0, 2));
    }

    public int getValue() {
        return this.value;
    }
}

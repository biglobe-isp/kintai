package domain;

/**
 * 出勤時間（MM）のValueObjectクラス。
 */
public class StartTimeMinuteVO {
    private final int value;

    public StartTimeMinuteVO(String startTime) {
        this.value = Integer.valueOf(startTime.substring(9, 11));
    }

    public int getValue() {
        return this.value;
    }
}

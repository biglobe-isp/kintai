package domain.japan;

/**
 * 退勤時間（MM）のValueObjectクラス。
 */
public class EndTimeMinuteVO {
    private final int value;

    public EndTimeMinuteVO(String endTime) {
        this.value = Integer.valueOf(endTime.substring(7, 9));
    }

    public int getValue() {
        return this.value;
    }

}

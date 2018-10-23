package domain;

/**
 * 退勤時間（HH）のValueObjectクラス。
 */
public class EndTimeHourVO {
    private final int value;

    public EndTimeHourVO(String endTime) {
        this.value = Integer.valueOf(endTime.substring(5, 7));
    }

    public int getValue() {
        return this.value;
    }
}
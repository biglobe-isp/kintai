package domain.japan;

/**
 * 出勤時間（HH）のValueObjectクラス。
 */
public class StartTimeHourVO {
    private final int value;

    public StartTimeHourVO(String startTime) {
        this.value = Integer.valueOf(startTime.substring(7, 9));
    }

    public int getValue() {
        return this.value;
    }
}

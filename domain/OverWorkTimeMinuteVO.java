package domain;

/**
 * 残業時間（分）のValueObjectクラス。
 */
public class OverWorkTimeMinuteVO {

    private final int value;

    public OverWorkTimeMinuteVO(WorkTimeMinuteVO workTimeMinuteVO){
        this.value = Math.max(workTimeMinuteVO.getValue() - 8 * 60, 0);
    }

    public int getValue() {
        return value;
    }
}

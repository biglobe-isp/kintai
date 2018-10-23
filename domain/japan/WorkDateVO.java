package domain.japan;

/**
 * 勤務日（YYYYMMDD）のValueObjectクラス。
 */
public class WorkDateVO {

    private final String value;

    public WorkDateVO(String workDate) {
        this.value = workDate.substring(6, 14);
    }

    public String getValue() {
        return this.value;
    }
}

package domain;

/**
 * 勤務年月（YYYYMM）のValueObjectクラス。
 */
public class WorkYearMonthVO {

    private final String value;

    public WorkYearMonthVO(String workYearMonth) {
        this.value = workYearMonth.substring(6, 12);
    }

    public String getValue() {
        return value;
    }
}

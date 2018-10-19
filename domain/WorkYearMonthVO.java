package domain;

/**
 * 勤務年月（YYYYMM）のValueObjectクラス。
 */
public class WorkYearMonthVO {

    private final String workYearMonth;

    public WorkYearMonthVO(String workYearMonth) {
        this.workYearMonth = workYearMonth;
    }

    public String getWorkYearMonth() {
        return workYearMonth;
    }
}

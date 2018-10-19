package domain;

/**
 * 勤務日（YYYYMMDD）のValueObjectクラス。
 */
public class WorkDateVO {

    private final String workDate;

    public WorkDateVO(String workDate) {
        this.workDate = workDate;
    }

    public String getWorkDate() {
        return workDate;
    }
}

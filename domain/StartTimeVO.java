package domain;

/**
 * 出勤時間（HHMM）のValueObjectクラス。
 */
public class StartTimeVO {

    private final String startTimeD;

    public StartTimeVO(String startTimeD) {
        this.startTimeD = startTimeD;
    }

    public String getStartTimeD() {
        return startTimeD;
    }

}
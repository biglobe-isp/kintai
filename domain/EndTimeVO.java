package domain;

/**
 * 退勤時間（HHMM）のValueObjectクラス。
 */
public class EndTimeVO {

    private final String endTimeD;

    public EndTimeVO(String endTimeD) {
        this.endTimeD = endTimeD;
    }

    public String getEndTimeD() {
        return endTimeD;
    }

}
package jp.co.esumit.kintai.domain.kintai_info.overtime_minutes;

import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutes;

public class OvertimeMinutesCalculator {
    //　定時
    private int fixedTime = 8 * 60;

    public OvertimeMinutes calc(OfficeMinutes officeMins) {
        return new OvertimeMinutes(Math.max(0, officeMins.getValue() - fixedTime));
    }
}

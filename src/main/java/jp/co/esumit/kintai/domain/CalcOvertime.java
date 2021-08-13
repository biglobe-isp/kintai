package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.datasource.AppConst;
import jp.co.esumit.kintai.domain.field.OfficeMinutes;
import jp.co.esumit.kintai.domain.field.Overtime;

public class CalcOvertime {

    public Overtime getOverTime(OfficeMinutes officeMins){
        return new Overtime(Math.max(0, officeMins.getValue() - AppConst.FIXED_MINUTES));
    }
}

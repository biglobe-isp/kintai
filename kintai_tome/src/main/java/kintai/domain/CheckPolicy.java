package kintai.domain;

import kintai.domain.SyukkinDate;
import kintai.domain.SyukkinTime;
import kintai.domain.TaikinTime;
import kintai.domain.company.KintaiCheckStatus;
import kintai.domain.company.SigyoTime;

public class CheckPolicy {

    public static KintaiCheckStatus check(SyukkinDate syukkinDate, SyukkinTime syukkinTime, TaikinTime taikinTime) {

        SigyoTime sigyoTime = new SigyoTime();

        if (!syukkinDate.isLocalDate()) {
            return KintaiCheckStatus.出勤日ありえない;
        }
        if (syukkinTime.getLocalTimeValue().compareTo(sigyoTime.getValue()) > 0) {
            return KintaiCheckStatus.開始時刻が始業時刻を超えてる;
        }

        return KintaiCheckStatus.OK;
    }
}

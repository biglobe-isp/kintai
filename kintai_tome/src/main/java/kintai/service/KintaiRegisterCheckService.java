package kintai.service;

import kintai.domain.CheckPolicy;
import kintai.domain.company.KintaiCheckStatus;
import kintai.domain.SyukkinDate;
import kintai.domain.SyukkinTime;
import kintai.domain.TaikinTime;

public class KintaiRegisterCheckService {

    public KintaiCheckStatus check(SyukkinDate date, SyukkinTime start, TaikinTime end) {

        return CheckPolicy.check(date, start, end);
    }

}

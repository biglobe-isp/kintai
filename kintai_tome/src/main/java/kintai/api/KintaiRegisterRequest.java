package kintai.api;

import kintai.domain.SyukkinDate;
import kintai.domain.SyukkinTime;
import kintai.domain.TaikinTime;

public class KintaiRegisterRequest {

    SyukkinDate syukkinDate;
    SyukkinTime syukkinTime;
    TaikinTime taikinTime;

    public KintaiRegisterRequest(String arg1, String arg2, String arg3) {
        this.syukkinDate = new SyukkinDate(arg1);
        this.syukkinTime = new SyukkinTime(arg2);
        this.taikinTime = new TaikinTime(arg3);
    }

    public SyukkinDate getArg1() {
        return syukkinDate;
    }

    public SyukkinTime getArg2() {
        return syukkinTime;
    }

    public TaikinTime getArg3() {
        return taikinTime;
    }
}

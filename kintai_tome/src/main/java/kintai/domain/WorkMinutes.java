package kintai.domain;

public class WorkMinutes {

    private FixedMinutes fixedMinutes;
    private OverWorkMinutes overWorkMinutes;

    public WorkMinutes(FixedMinutes fixedMinutes, OverWorkMinutes overWorkMinutes) {
        this.fixedMinutes = fixedMinutes;
        this.overWorkMinutes = overWorkMinutes;
    }

    public static WorkMinutes create(SyukkinTime syukkinTime, TaikinTime taikinTime, AllBreakMinutes allBreakMinutes) {

        int workMinutes = taikinTime.getLocalTimeValue().getHour() * 60 + taikinTime.getLocalTimeValue().getMinute() - (syukkinTime.getLocalTimeValue().getHour() * 60 + syukkinTime.getLocalTimeValue().getMinute());
        workMinutes -= allBreakMinutes.getValue();

        FixedMinutes fixedMinutes = FixedMinutes.create(syukkinTime, taikinTime);
        OverWorkMinutes overWorkMinutes = new OverWorkMinutes(Math.max(workMinutes - fixedMinutes.getValue(), 0));

        return new WorkMinutes(
                fixedMinutes,
                overWorkMinutes
        );
    }

    public int getValue() {
        return fixedMinutes.getValue() + overWorkMinutes.getValue();
    }

    FixedMinutes getFixedMinutes() {
        return fixedMinutes;
    }

    OverWorkMinutes getOverWorkMinutes() {

        return overWorkMinutes;
    }

}

package com.naosim.dddwork.domain.workdateandtime;


import lombok.Getter;

public class WorkTimeMinutes {

    private WorkDateAndTime workDateAndTime;

    @Getter
    private final AllWorkTimeMinutes allWorkTimeMinutes;

    @Getter
    private final OverWorkTimeMinutes overWorkTimeMinutes;

    /**
     * コンストラクタ
     *
     * @param workDateAndTime
     */
    public WorkTimeMinutes(WorkDateAndTime workDateAndTime) {
        this.workDateAndTime = workDateAndTime;
        this.allWorkTimeMinutes = new AllWorkTimeMinutes(this.workDateAndTime);
        this.overWorkTimeMinutes = new OverWorkTimeMinutes(allWorkTimeMinutes);
    }
}

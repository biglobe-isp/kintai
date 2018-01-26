package com.naosim.dddwork.domain.kintai.register;


import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.time.work.OverWorkMinutes;
import com.naosim.dddwork.domain.kintai.time.work.WorkMinutes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class KintaiRegister {

    @Getter
    private final WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay;

    public KintaiRegister(WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay) {
        this.workStartAndEndTimeOfOneDay = workStartAndEndTimeOfOneDay;
    }

    public KintaiOfOneDay getKintaiOfOneDay() {
        WorkMinutes workMinutes = WorkMinutes.get(
                this.workStartAndEndTimeOfOneDay.getWorkStartTime(),
                this.workStartAndEndTimeOfOneDay.getWorkEndTime()
        );

        OverWorkMinutes overWorkMinutes = OverWorkMinutes.get(workMinutes);

        return new KintaiOfOneDay(
                this.workStartAndEndTimeOfOneDay.getWorkDate(),
                this.workStartAndEndTimeOfOneDay.getWorkStartTime(),
                this.workStartAndEndTimeOfOneDay.getWorkEndTime(),
                workMinutes,
                overWorkMinutes,
                this.workStartAndEndTimeOfOneDay.getNow()
        );
    }
}

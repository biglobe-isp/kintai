package com.naosim.dddwork.domain.kintai;


import com.naosim.dddwork.domain.kintai.time.work.OverWorkMinutes;
import com.naosim.dddwork.domain.kintai.time.work.WorkEndTime;
import com.naosim.dddwork.domain.kintai.time.work.WorkMinutes;
import com.naosim.dddwork.domain.kintai.time.work.WorkStartTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class KintaiRegist {

    @Getter
    private final WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay;

    @Getter
    private final WorkStartTime workStartTime;

    @Getter
    private final WorkEndTime workEndTime;

    public KintaiRegist(WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay) {
        this.workStartAndEndTimeOfOneDay = workStartAndEndTimeOfOneDay;

        this.workStartTime = workStartAndEndTimeOfOneDay.getWorkStartTime();
        this.workEndTime = workStartAndEndTimeOfOneDay.getWorkEndTime();
    }

    public KintaiOfOneDay getKintaiOfOneDay() {
        WorkMinutes workMinutes = WorkMinutes.get(this.workStartTime, this.workEndTime);

        OverWorkMinutes overWorkMinutes = OverWorkMinutes.get(workMinutes);

        return new KintaiOfOneDay(
                this.workStartAndEndTimeOfOneDay.getWorkWorkDate(),
                this.workStartAndEndTimeOfOneDay.getWorkStartTime(),
                this.workStartAndEndTimeOfOneDay.getWorkEndTime(),
                workMinutes,
                overWorkMinutes,
                this.workStartAndEndTimeOfOneDay.getNow()
        );
    }
}

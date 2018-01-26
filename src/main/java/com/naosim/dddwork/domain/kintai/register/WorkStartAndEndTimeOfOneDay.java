package com.naosim.dddwork.domain.kintai.register;

import com.naosim.dddwork.domain.kintai.time.Now;
import com.naosim.dddwork.domain.kintai.time.work.WorkDate;
import com.naosim.dddwork.domain.kintai.time.work.WorkEndTime;
import com.naosim.dddwork.domain.kintai.time.work.WorkStartTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkStartAndEndTimeOfOneDay {

    @Getter
    private final WorkDate workDate;

    @Getter
    private final WorkStartTime workStartTime;

    @Getter
    private final WorkEndTime workEndTime;

    @Getter
    private final Now now;
}

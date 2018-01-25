package com.naosim.dddwork.domain.kintai;

import com.naosim.dddwork.domain.time.Now;
import com.naosim.dddwork.domain.time.work.WorkDate;
import com.naosim.dddwork.domain.time.work.WorkEndTime;
import com.naosim.dddwork.domain.time.work.WorkStartTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkStartAndEndTimeOfOneDay {

    @Getter
    private final WorkDate workWorkDate;

    @Getter
    private final WorkStartTime workStartTime;

    @Getter
    private final WorkEndTime workEndTime;

    @Getter
    private final Now now;
}

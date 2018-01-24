package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.time.Now;
import com.naosim.dddwork.domain.time.work.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class KintaiOfOneDay {

    @Getter
    private final WorkDate workDate;

    @Getter
    private final WorkStartTime workStartTime;

    @Getter
    private final WorkEndTime workEndTime;

    @Getter
    private final WorkMinutes workMinutes;

    @Getter
    private final OverWorkMinutes overWorkMinutes;

    @Getter
    private final Now now;
}

package com.naosim.dddwork.domain.workdateandtime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 勤怠時間を入力する。
 */
@RequiredArgsConstructor
public class WorkDateAndTime {

    @Getter
    private final WorkDate workDate;

    @Getter
    private final WorkTimeStart workTimeStart;

    @Getter
    private final WorkTimeEnd workTimeEnd;

    @Getter
    private final WorkTimeNow workTimeNow;
}

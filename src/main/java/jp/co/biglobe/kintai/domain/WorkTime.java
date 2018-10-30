package jp.co.biglobe.kintai.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkTime {

    @Getter
    private final WorkDate date;

    @Getter
    private final StartWorkTime startTime;

    @Getter
    private final EndWorkTime endTime;

    @Getter
    private final int minutes;

    @Getter
    private final int overWorkMinutes;

    @Getter
    private final NowTime now;
}

package com.naosim.dddwork.kintai_management.domain.word;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 残業時間
 */
@AllArgsConstructor
public class OverWorkingTime  {

    @Getter
    private final Integer value;

    public static OverWorkingTime create(WorkingStartTime workingStartTime, WorkingEndTime workingEndTime, HolidayKind holidayKind) {
        return new OverWorkingTime(Integer.valueOf(Math.max(WorkingTime.create(workingStartTime, workingEndTime, holidayKind).getValue() - 8 * 60, 0)));
    }

}

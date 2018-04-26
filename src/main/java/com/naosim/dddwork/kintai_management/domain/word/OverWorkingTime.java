package com.naosim.dddwork.kintai_management.domain.word;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 残業時間
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class OverWorkingTime  {
    @Getter
    private final Integer value;

    public static OverWorkingTime blank() {
        return new OverWorkingTime(null);
    }

    public static OverWorkingTime create(WorkingStartTime workingStartTime, WorkingEndTime workingEndTime, HolidayKind holidayKind) {
        return new OverWorkingTime(Integer.valueOf(Math.max(WorkingTime.create(workingStartTime, workingEndTime, holidayKind).getValue() - 8 * 60, 0)));
    }

}

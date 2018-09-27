package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.attendance.TotalYearMonth;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class AttendanceTotalInquiry {

    @Getter
    private final TotalYearMonth totalYearMonth;
}

package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.WorkingMonth;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class TotalWorkingHoursApplication {
    @Getter
    private  final WorkingMonth workingMonth;
}

package com.naosim.dddwork.domain.monthlysummary;

import com.naosim.dddwork.domain.TimeUnit;
import lombok.Value;

@Value(staticConstructor = "of")
public class MonthlySummary {
    YearMonth workMonth;
    TimeUnit workingHours;
    TimeUnit overTimeHours;
}

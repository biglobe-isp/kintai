package com.naosim.dddwork.domain.monthlysummary;

import com.google.common.base.Strings;
import lombok.Value;

@Value (staticConstructor = "of")
public class YearMonth {
    int year;
    int month;

    public String toString() {
        return Strings.padStart(String.valueOf(year), 4, '0')
                + Strings.padStart(String.valueOf(month), 2, '0');
    }
}

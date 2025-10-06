package org.example.controller;

import org.example.domain.WorkTime;

import java.time.YearMonth;

import static org.example.controller.FormatParser.parseData;
import static org.example.utils.DateTimeFormatters.YEAR_HYPHEN_MONTH;

public class GetTotalTimeCommandLine {
    public static YearMonth of(String[] inputYearMonth) {
        if (inputYearMonth.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments. Expected 1 argument: yearMonth.");
        }

        String yearMonth = parseData(inputYearMonth[0]);

        return YearMonth.parse(yearMonth, YEAR_HYPHEN_MONTH);
    }

    public static String getTotalTime(WorkTime workTime) {
        return String.format(
                "Total Working Hours: %d, Total Over Time Hours: %d",
                workTime.getTotalWorkingHours().getValue(),
                workTime.getTotalOverTimeHours().getValue()
        );
    }
}

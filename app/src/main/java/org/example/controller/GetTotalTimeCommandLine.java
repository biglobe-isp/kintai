package org.example.controller;

import org.example.domain.TotalHours;

import static org.example.controller.FormatParser.parseDataOfCoron;

public class GetTotalTimeCommandLine {
    public static String toYearMonth(String[] inputYearMonth) {
        if (inputYearMonth.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments. Expected 1 argument: yearMonth.");
        }

        return parseDataOfCoron(inputYearMonth[0]);
    }

    public static String getTotalTime(TotalHours totalHours) {
        return String.format(
                "Total Working Hours: %d, Total Over Time Hours: %d",
                totalHours.getTotalWorkingHours().getValue(),
                totalHours.getTotalOverTimeHours().getValue()
        );
    }
}

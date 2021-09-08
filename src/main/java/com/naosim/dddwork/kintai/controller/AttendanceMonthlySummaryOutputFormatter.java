package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceMonthlySummary;
import org.springframework.stereotype.Component;

@Component
class AttendanceMonthlySummaryOutputFormatter {
    String toAllOutput(AggregationMonth aggregationMonth, AttendanceMonthlySummary attendanceMonthlySummary) {
        StringBuilder builder = new StringBuilder();
        String separator = System.getProperty("line.separator");
        builder
                .append(getTargetYearMonth(aggregationMonth))
                .append(separator)
                .append(getWorkingTime(attendanceMonthlySummary))
                .append(separator)
                .append(getOvertime(attendanceMonthlySummary));
        return builder.toString();
    }

    private String getTargetYearMonth(AggregationMonth aggregationMonth) {
        return "集計対象：" + aggregationMonth.getYearMonth();
    }

    private String getWorkingTime(AttendanceMonthlySummary attendanceMonthlySummary) {
        return "総労働時間：" +
                String.format(
                        "%s時間%s分",
                        attendanceMonthlySummary.convertActualWorkingTimeMinutesToHour(),
                        attendanceMonthlySummary.extractRemainderActualWorkingTimeMinutes()
                );
    }

    private String getOvertime(AttendanceMonthlySummary attendanceMonthlySummary) {
        return "総残業時間：" +
                String.format(
                        "%s時間%s分",
                        attendanceMonthlySummary.convertActualOvertimeMinutesToHour(),
                        attendanceMonthlySummary.extractRemainderActualOvertimeMinutes()
                );
    }
}

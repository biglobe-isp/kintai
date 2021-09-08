package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceMonthlySummary;
import lombok.NonNull;
import lombok.Value;

@Value
class AttendanceMonthlySummaryOutputItem {
    @NonNull
    AggregationMonth aggregationMonth;
    @NonNull
    AttendanceMonthlySummary attendanceMonthlySummary;
    
    String toAllOutput() {
        StringBuilder builder = new StringBuilder();
        String separator = System.getProperty("line.separator");
        builder
                .append(getTargetYearMonth())
                .append(separator)
                .append(getWorkingTime())
                .append(separator)
                .append(getOvertime());
        return builder.toString();
    }

    private String getTargetYearMonth() {
        return "集計対象：" + this.aggregationMonth.getYearMonth();
    }

    private String getWorkingTime() {
        return "総労働時間：" + String.format("%s時間%s分",
                                       this.attendanceMonthlySummary.convertActualWorkingTimeMinutesToHour(),
                                       this.attendanceMonthlySummary.extractRemainderActualWorkingTimeMinutes());
    }
    private String getOvertime() {
        return "総残業時間：" + String.format("%s時間%s分",
                                       this.attendanceMonthlySummary.convertActualOvertimeMinutesToHour(),
                                       this.attendanceMonthlySummary.extractRemainderActualOvertimeMinutes());
    }
}

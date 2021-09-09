package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceMonthlySummary;
import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.service.aggregation.AttendanceAggregationService;
import com.naosim.dddwork.kintai.service.timerecord.AttendanceRecordingService;
import com.naosim.dddwork.kintai.service.timerecord.LatenessCheckingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AttendanceController {
    //TODO: 凝集度を上げる
    private final AttendanceRecordingService attendanceRecordingService;
    private final AttendanceAggregationService attendanceAggregationService;
    private final LatenessCheckingService latenessCheckingService;
    private final AttendanceOutputFormatter attendanceOutputFormatter;
    private final AttendanceMonthlySummaryOutputFormatter attendanceMonthlySummaryOutputFormatter;

    public void record(String ymd, String startTime, String endTime) {
        try {
            AttendanceInputParameter parameter = new AttendanceInputParameter(ymd, startTime, endTime);
            AttendanceDate attendanceDate = new AttendanceDate(parameter.getYmd());
            // 遅刻なら即クビ、それ以外は受け入れる
            AttendanceTimeInterval attendanceTimeInterval = latenessCheckingService.acceptOrFire(
                    attendanceDate,
                    new TimeInterval(
                            new StartTime(parameter.getYmd(), parameter.getStartTime()),
                            new EndTime(parameter.getYmd(), parameter.getEndTime())
                    )
            );
            AttendanceRecord attendanceRecord = attendanceRecordingService.record(
                    attendanceDate,
                    attendanceTimeInterval
            );
            System.out.println(attendanceOutputFormatter.toAllOutput(attendanceRecord));
        } catch (Exception e) {
            System.out.println("打刻に失敗しました。");
            e.printStackTrace();
        }
    }

    public void aggregateMonthly(String yearMonth) {
        AggregationMonthInputParameter parameter = new AggregationMonthInputParameter(yearMonth);
        AggregationMonth aggregationMonth = new AggregationMonth(parameter.getYearMonth());
        try {
            AttendanceMonthlySummary attendanceMonthlySummary = attendanceAggregationService.aggregateMonthly(
                    aggregationMonth);
            System.out.println(attendanceMonthlySummaryOutputFormatter.toAllOutput(
                    aggregationMonth,
                    attendanceMonthlySummary
            ));
        } catch (Exception e) {
            System.out.println("月次集計に失敗しました。");
            e.printStackTrace();
        }
    }
}

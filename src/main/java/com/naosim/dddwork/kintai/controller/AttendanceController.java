package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceMonthlySummary;
import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeIntervalFactory;
import com.naosim.dddwork.kintai.service.aggregation.AttendanceAggregationService;
import com.naosim.dddwork.kintai.service.timerecord.AttendanceRecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AttendanceController {
    private final AttendanceRecordingService attendanceRecordingService;
    private final AttendanceAggregationService attendanceAggregationService;
    private final AttendanceTimeIntervalFactory attendanceTimeIntervalFactory;

    public void record(String[] inputParams) {
        try {
            AttendanceDate attendanceDate = new AttendanceDate(inputParams[0]);
            AttendanceTimeInterval attendanceTimeInterval = attendanceTimeIntervalFactory.create(
                    attendanceDate,
                    new TimeInterval(
                            new StartTime(attendanceDate, inputParams[1]),
                            new EndTime(attendanceDate, inputParams[2])
                    )
            );
            AttendanceRecord attendanceRecord = attendanceRecordingService.record(
                    attendanceDate,
                    attendanceTimeInterval
            );
            outputAttendanceRecord(attendanceRecord);
        } catch (Exception e) {
            System.out.println("打刻に失敗しました。");
            e.printStackTrace();
        }
    }

    public void aggregateMonthly(String[] inputParams) {
        AggregationMonth aggregationMonth = new AggregationMonth(inputParams[0]);
        try {
            AttendanceMonthlySummary aggregationMonthly = attendanceAggregationService.aggregateMonthly(
                    aggregationMonth);
            outputAggregationMonthly(aggregationMonth, aggregationMonthly);
        } catch (Exception e) {
            System.out.println("月次集計に失敗しました。");
            e.printStackTrace();
        }
    }

    private void outputAttendanceRecord(AttendanceRecord attendanceRecord) {
        System.out.println(new AttendanceOutputItem(attendanceRecord).toAllOutput());
    }

    private void outputAggregationMonthly(
            AggregationMonth aggregationMonth,
            AttendanceMonthlySummary aggregationMonthlySummary) {

        System.out.println(
                new AttendanceMonthlySummaryOutputItem(
                        aggregationMonth,
                        aggregationMonthlySummary
                ).toAllOutput()
        );
    }
}

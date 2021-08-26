package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceAggregationMonthly;
import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.service.aggregation.AttendanceAggregationService;
import com.naosim.dddwork.kintai.service.timerecord.AttendanceRecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AttendanceController {
    private final AttendanceRecordingService attendanceRecordingService;
    private final AttendanceAggregationService attendanceAggregationService;

    public void record(String[] inputParams) {
        try {
            AttendanceDate attendanceDate = new AttendanceDate(inputParams[0]);
            // TODO: Factoryを用意する
            AttendanceTimeInterval attendanceTimeInterval = new AttendanceTimeInterval(
                    new TimeInterval(
                        new StartTime(attendanceDate.getZonedDateTime(), inputParams[1]),
                        new EndTime(attendanceDate.getZonedDateTime(), inputParams[2])
                    )
            );
            AttendanceRecord attendanceRecord = attendanceRecordingService.record(attendanceDate, attendanceTimeInterval);
            // TODO: ControllerでSysoutしない
            System.out.println("打刻が完了しました。");
            System.out.println("出勤日" + attendanceRecord.getAttendanceLocalDate());
            System.out.println("出勤時間" + attendanceRecord.getAttendanceStartLocalTime() + " ~ " + attendanceRecord.getAttendanceEndLocalTime());
            // TODO: N時間N分のような形で出力する
            System.out.println("労働時間（分）" + attendanceRecord.getActualWorkingTimeMinutes().intValue());
            System.out.println("残業時間（分）" + attendanceRecord.getActualOvertimeMinutes().intValue());
        } catch(Exception e) {
            System.out.println("打刻に失敗しました。");
            e.printStackTrace();
        }
    }

    public void aggregateMonthly(String[] inputParams) {
        AggregationMonth aggregationMonth = new AggregationMonth(inputParams[0]);
        AttendanceAggregationMonthly aggregationMonthly = attendanceAggregationService.aggregateMonthly(aggregationMonth);
        System.out.println("集計対象：" + aggregationMonth.getYearMonth());
        // TODO: ControllerでSysoutしない
        // TODO: N時間N分のような形で出力する
        // TODO: if文やめる
        if (aggregationMonthly.getTotalOvertimeMinutes().isPresent()) {
            System.out.println("労働時間合計は" + aggregationMonthly.getTotalWorkingTimeMinutes().get() + "分です。");
            System.out.println("残業時間合計は" + aggregationMonthly.getTotalOvertimeMinutes().get() + "分です。");
        } else {
            System.out.println("対象の月は働いていません。");
        }
    }

}

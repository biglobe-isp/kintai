package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.service.timerecord.AttendanceRecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AttendanceController {
    private final AttendanceRecordingService attendanceRecordingService;

    public void record(String[] inputParams) {
        try {
            AttendanceDate attendanceDate = new AttendanceDate(inputParams[0]);
            AttendanceTimeInterval attendanceTimeInterval = new AttendanceTimeInterval(
                    new StartTime(attendanceDate.getZonedDateTime(), inputParams[1]),
                    new EndTime(attendanceDate.getZonedDateTime(), inputParams[2])
            );
            AttendanceRecord attendanceRecord = attendanceRecordingService.record(attendanceDate, attendanceTimeInterval);
            // TODO: ControllerでSysoutしない
            System.out.println("打刻が完了しました。");
            System.out.println("出勤日" + attendanceRecord.getYmd());
            System.out.println("出勤時間" + attendanceRecord.getStartTime() + " ~ " + attendanceRecord.getEndTime());
            // TODO: N時間N分のような形で出力する
            System.out.println("労働時間（分）" + attendanceRecord.getWorkingTimeMinutes());
            System.out.println("残業時間（分）" + attendanceRecord.getOvertimeMinutes());
        } catch(Exception e) {
            System.out.println("打刻に失敗しました。");
            e.printStackTrace();
        }
    }

    public void aggregateMonthly(String[] inputParams) {
        // TODO:
    }

}

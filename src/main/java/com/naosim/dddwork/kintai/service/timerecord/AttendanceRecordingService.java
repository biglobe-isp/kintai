package com.naosim.dddwork.kintai.service.timerecord;

import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.breaktime.ActualBreakTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.overtime.ActualOvertimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.workingtime.ActualWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.service.AttendanceRepository;
import com.naosim.dddwork.kintai.service.RegulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendanceRecordingService {

    private final AttendanceRepository attendanceRepository;
    private final RegulationRepository regulationRepository;

    public AttendanceRecord record(AttendanceDate attendanceDate, AttendanceTimeInterval attendanceTimeInterval) throws Exception {
        // 就業規則をとってくる（休憩時間、規定労働時間（分））
        RegulatedBreakTimeShift regulatedBreakTimeShift = regulationRepository.fetchBreakTimeShift(attendanceDate);
        RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes = regulationRepository.fetchRegulatedWorkingTimeMinutes(attendanceDate);
        // 勤務時間（拘束時間）を取得
        AttendanceTimeMinutes attendanceTimeMinutes = new AttendanceTimeMinutes(attendanceTimeInterval);
        // 実休憩時間を取得
        ActualBreakTimeMinutes actualBreakTimeMinutes = new ActualBreakTimeMinutes(attendanceTimeInterval, regulatedBreakTimeShift);
        // 実労働時間を取得
        ActualWorkingTimeMinutes actualWorkingTimeMinutes = new ActualWorkingTimeMinutes(attendanceTimeMinutes, actualBreakTimeMinutes);
        // 残業時間を取得
        ActualOvertimeMinutes overtimeMinutes = new ActualOvertimeMinutes(actualWorkingTimeMinutes, regulatedWorkingTimeMinutes);

        AttendanceRecord attendanceRecord = new AttendanceRecord(attendanceDate, attendanceTimeInterval,
                                                                          actualWorkingTimeMinutes, overtimeMinutes);
        // 登録
        attendanceRepository.register(attendanceRecord);

        return attendanceRecord;
    }
}

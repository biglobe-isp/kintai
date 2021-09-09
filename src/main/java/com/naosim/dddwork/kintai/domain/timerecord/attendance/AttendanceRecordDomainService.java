package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.actualtime.ActualMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.overtime.ActualOvertimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.workingtime.ActualWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import org.springframework.stereotype.Component;

// VO -> 引数でパラメータをわたして、Constructorで受け取る、メンバー変数を使ってメソッドを呼ぶ　カプセル化の練習にも良い
// DomainService -> 蘇結合になる、テストしやすい
@Component
public class AttendanceRecordDomainService {
    public AttendanceRecord createAttendanceRecord(
            AttendanceDate attendanceDate,
            AttendanceTimeInterval attendanceTimeInterval,
            RegulatedBreakTimeShift regulatedBreakTimeShift,
            RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes) {

        // 勤務時間を取得
        AttendanceTimeMinutes attendanceTimeMinutes = new AttendanceTimeMinutes(attendanceTimeInterval);

        // 実労働時間を取得(勤務時間 - 実休憩時間)
        ActualWorkingTimeMinutes actualWorkingTimeMinutes = new ActualWorkingTimeMinutes(
                attendanceTimeMinutes.excludeBreakTime(attendanceTimeInterval, regulatedBreakTimeShift)
        );

        // 残業時間を取得(実労働時間 - 規定労働時間)
        ActualOvertimeMinutes actualOvertimeMinutes = new ActualOvertimeMinutes(
                actualWorkingTimeMinutes,
                regulatedWorkingTimeMinutes
        );

        return new AttendanceRecord(
                attendanceDate,
                attendanceTimeInterval,
                new ActualMinutes(actualWorkingTimeMinutes, actualOvertimeMinutes)
        );
    }
}

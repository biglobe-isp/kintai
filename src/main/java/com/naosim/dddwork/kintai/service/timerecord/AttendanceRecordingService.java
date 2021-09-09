package com.naosim.dddwork.kintai.service.timerecord;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecordDomainService;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import com.naosim.dddwork.kintai.service.AttendanceRepository;
import com.naosim.dddwork.kintai.service.RegulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendanceRecordingService {
    private final AttendanceRepository attendanceRepository;
    private final RegulationRepository regulationRepository;
    private final AttendanceRecordDomainService attendanceRecordDomainService;

    public AttendanceRecord record(
            AttendanceDate attendanceDate,
            AttendanceTimeInterval attendanceTimeInterval) throws Exception {
        // 就業規則をとってくる（休憩時間、規定労働時間（分）)
        RegulatedBreakTimeShift regulatedBreakTimeShift = regulationRepository.fetchBreakTimeShift(attendanceDate);
        RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes = regulationRepository.fetchRegulatedWorkingTimeMinutes(
                attendanceDate);

        // 勤怠記録の作成

        // MEMO：引数を複数個渡すのではなくて、一塊のデータをつくって、そこにロジックを持たせる方が良い
        // 出勤情報オブジェクト、就業規則オブジェクトの2つを知っているオブジェクトが計算する方が良い
        // ⇨ビジネスルールがクラス図に現れるのではないか？
        // 複数方法を検討してメリット・デメリットを鑑みながらどちらかを採用する
        AttendanceRecord attendanceRecord = attendanceRecordDomainService.createAttendanceRecord(
                attendanceDate,
                attendanceTimeInterval,
                regulatedBreakTimeShift,
                regulatedWorkingTimeMinutes
        );

        // 登録
        attendanceRepository.register(attendanceRecord);

        return attendanceRecord;
    }
}

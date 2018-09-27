package com.naosim.dddwork.service.attendance;

import com.naosim.dddwork.domain.attendance.AttendanceHistory;
import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import com.naosim.dddwork.domain.attendance.TotalOverWorkMinutesByMonth;
import com.naosim.dddwork.domain.attendance.TotalWorkMinutesByMonth;
import com.naosim.dddwork.domain.use_case.AttendanceTotalInquiry;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤怠管理集計Service
 */
@Service
@NoArgsConstructor
public class AttendanceTotalService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void refer(AttendanceTotalInquiry attendanceTotalInquiry) throws Exception {

        // 勤怠リストの取得
        AttendanceHistory attendanceHistory =
                new AttendanceHistory(
                        attendanceRepository.find()
                                .orElseThrow(() -> new Exception("ファイルが見つかりません"))
                );

        // 労働時間、残業時間の集計
        TotalWorkMinutesByMonth totalWorkMinutesByMonth =
                attendanceHistory.totalWorkMinutesByMonth(attendanceTotalInquiry.getTotalYearMonth());

        TotalOverWorkMinutesByMonth totalOverWorkMinutesByMonth =
                attendanceHistory.totalOverWorkMinutesByMonth(attendanceTotalInquiry.getTotalYearMonth());

        // 労働時間、残業時間の出力
        attendanceHistory.print(totalWorkMinutesByMonth, totalOverWorkMinutesByMonth);
    }
}

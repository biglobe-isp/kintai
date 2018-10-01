package com.naosim.dddwork.service.attendance;

import com.naosim.dddwork.domain.attendance.AttendanceHistory;
import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import com.naosim.dddwork.domain.use_case.AttendanceTotalInquiry;
import com.naosim.dddwork.domain.use_case.AttendanceTotalInquiryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤怠管理集計Service
 * 2018/09/27 新規作成
 * 2018/09/28 レビュー指摘事項反映 Exceptionをthrowしないように修正
 * 2018/09/28 レビュー指摘事項反映 UI関連のメソッドをService層からUI層に移動する対応
 */
@Service
@NoArgsConstructor
public class AttendanceTotalService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public AttendanceTotalInquiryResponse refer(AttendanceTotalInquiry attendanceTotalInquiry) throws Exception {

        // 勤怠履歴の取得
        AttendanceHistory attendanceHistory = attendanceRepository.find();

        // 労働時間、残業時間の集計、および、返却
        return new AttendanceTotalInquiryResponse(
                attendanceHistory.totalWorkMinutesByMonth(attendanceTotalInquiry.getTotalYearMonth()),
                attendanceHistory.totalOverWorkMinutesByMonth(attendanceTotalInquiry.getTotalYearMonth())
        );
    }
}

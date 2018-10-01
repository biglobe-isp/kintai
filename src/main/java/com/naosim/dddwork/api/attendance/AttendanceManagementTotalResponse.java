package com.naosim.dddwork.api.attendance;

import com.naosim.dddwork.domain.attendance.TotalOverWorkMinutesByMonth;
import com.naosim.dddwork.domain.attendance.TotalWorkMinutesByMonth;
import com.naosim.dddwork.domain.use_case.AttendanceTotalInquiryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 2018/09/28 新規作成
 * 　　　レビュー指摘事項反映 UI関連のメソッドをService層からUI層に移動する対応
 */
@AllArgsConstructor
public class AttendanceManagementTotalResponse {

    @Getter
    private final TotalWorkMinutesByMonth totalWorkMinutesByMonth;

    @Getter
    private final TotalOverWorkMinutesByMonth totalOverWorkMinutesByMonth;

    public AttendanceManagementTotalResponse(AttendanceTotalInquiryResponse attendanceTotalInquiryResponse) {

        this.totalWorkMinutesByMonth = attendanceTotalInquiryResponse.getTotalWorkMinutesByMonth();

        this.totalOverWorkMinutesByMonth = attendanceTotalInquiryResponse.getTotalOverWorkMinutesByMonth();
    }

    public void print() {

        System.out.println("勤務時間: " +
                this.getTotalWorkMinutesByMonth().getValue() / 60 + "時間" +
                this.getTotalWorkMinutesByMonth().getValue() % 60 + "分");

        System.out.println("残業時間: " +
                this.getTotalOverWorkMinutesByMonth().getValue() / 60 + "時間" +
                this.getTotalOverWorkMinutesByMonth().getValue() % 60 + "分");
    }
}

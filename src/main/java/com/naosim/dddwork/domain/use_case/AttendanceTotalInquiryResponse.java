package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.attendance.TotalOverWorkMinutesByMonth;
import com.naosim.dddwork.domain.attendance.TotalWorkMinutesByMonth;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 2018/09/28 新規作成
 * 　　　レビュー指摘事項反映 UI関連のメソッドをService層からUI層に移動する対応
 */
@AllArgsConstructor
@EqualsAndHashCode
public class AttendanceTotalInquiryResponse {

    @Getter
    private final TotalWorkMinutesByMonth totalWorkMinutesByMonth;

    @Getter
    private final TotalOverWorkMinutesByMonth totalOverWorkMinutesByMonth;
}

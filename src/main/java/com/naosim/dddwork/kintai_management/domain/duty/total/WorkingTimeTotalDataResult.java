package com.naosim.dddwork.kintai_management.domain.duty.total;

import com.naosim.dddwork.kintai_management.domain.word.TotalOverWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 勤怠集計情報結果
 */
@AllArgsConstructor
@Getter
public class WorkingTimeTotalDataResult {

    private TotalYearMonth totalYearMonth;

    private TotalWorkingTime totalWorkingTime;

    private TotalOverWorkingTime totalOverWorkingTime;
}

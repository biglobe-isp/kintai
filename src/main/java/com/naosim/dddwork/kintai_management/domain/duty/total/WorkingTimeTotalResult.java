package com.naosim.dddwork.kintai_management.domain.duty.total;

import com.naosim.dddwork.kintai_management.domain.word.TotalOverWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 勤怠集計結果情報
 */
@AllArgsConstructor
@Getter
public class WorkingTimeTotalResult {

    private TotalYearMonth totalYearMonth;

    private TotalWorkingTime totalWorkingTime;

    private TotalOverWorkingTime totalOverWorkingTime;
}

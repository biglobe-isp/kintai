package com.naosim.dddwork.kintai_management.domain.duty.total;

import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 勤怠集計情報入力
 */
@AllArgsConstructor
@Getter
public class WorkingTimeTotalDataInput {

    private TotalYearMonth totalYearMonth;

}

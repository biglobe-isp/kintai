package com.naosim.dddwork.kintai_management.domain.duty.total;

import com.naosim.dddwork.kintai_management.domain.word.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 勤怠集計入力情報
 */
@AllArgsConstructor
@Getter
public class WorkingTimeTotalInput {

    private TotalYearMonth totalYearMonth;

}

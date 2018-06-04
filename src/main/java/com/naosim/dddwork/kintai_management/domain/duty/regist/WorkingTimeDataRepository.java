package com.naosim.dddwork.kintai_management.domain.duty.regist;

import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataInput;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataResult;

/**
 * 勤怠情報リポジトリインタフェース
 */
public interface WorkingTimeDataRepository {

        void registWorkingTime(WorkingTimeDataInput workingTimeRegistrationInput);

        WorkingTimeTotalDataResult getTotalWorkingTime(WorkingTimeTotalDataInput workingTimeTotalInput);
}

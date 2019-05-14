package com.naosim.dddwork.kintai.domain.repository.protocol;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;


/**
 * ［勤務時間］リポジトリ規定
 */
public interface WorkedTimeRepository {

    void save(DailyWorkedTime dailyWorkedTime);
    MonthlyTotalWorkedTime totalWorkedTimeIn(AttendanceYearMonth yearMonth);
}

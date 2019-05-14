package com.naosim.dddwork.kintai.datasource.workedtime.csv;

import com.naosim.dddwork.kintai.datasource.workedtime.csv.command.DailyWorkedTimeRegistration;
import com.naosim.dddwork.kintai.datasource.workedtime.csv.query.MonthlyTotalWorkedTimeQuery;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;
import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;


/**
 * 勤務時間データソース (CSVデータストア)
 */
public class WorkedTimeCsvDataSource implements WorkedTimeRepository {

    @Override
    public void save(DailyWorkedTime dailyWorkedTime) {
        new DailyWorkedTimeRegistration().save(dailyWorkedTime);
    }

    @Override
    public MonthlyTotalWorkedTime totalWorkedTimeIn(AttendanceYearMonth yearMonth) {
        return new MonthlyTotalWorkedTimeQuery().totalWorkedTimeIn(yearMonth);
    }
}

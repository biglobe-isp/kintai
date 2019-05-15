package com.naosim.dddwork.kintai.service.query;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;
import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;
import lombok.AllArgsConstructor;
import lombok.Value;


/**
 * ［指定月の勤務時間合計表示］サービス
 */
public class MonthlyTotalWorkedTimeQueryService {

    @Value
    @AllArgsConstructor(staticName="of")
    public static class Parameter {

        final AttendanceYearMonth attendanceYearMonth;
    }

    final WorkedTimeRepository repository;


    public MonthlyTotalWorkedTimeQueryService(WorkedTimeRepository repository) {
        this.repository = repository;
    }



    public MonthlyTotalWorkedTime execute(Parameter parameter) {
        return _showWorkedTime(parameter);
    }

    private MonthlyTotalWorkedTime _showWorkedTime(Parameter parameter) {

        AttendanceYearMonth yearMonth = parameter.attendanceYearMonth;
        return repository.totalWorkedTimeIn(yearMonth);
    }
}

package com.naosim.dddwork.kintai.service.query;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;
import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;
import com.naosim.dddwork.kintai.shared.exception.SystemException;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.IOException;


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

        try {
            return _showWorkedTime(parameter);
        }
        catch (IOException e) {
            throw new SystemException("月次勤務時間合計照会処理中に入出力例外が発生しました．", e);
        }
    }

    private MonthlyTotalWorkedTime _showWorkedTime(Parameter parameter) throws IOException {

        AttendanceYearMonth yearMonth = parameter.attendanceYearMonth;
        return repository.totalWorkedTimeIn(yearMonth);
    }
}

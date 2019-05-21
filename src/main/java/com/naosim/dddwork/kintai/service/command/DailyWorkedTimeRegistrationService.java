package com.naosim.dddwork.kintai.service.command;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkBeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkEndTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyTimeRecord;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;
import lombok.AllArgsConstructor;
import lombok.Value;


/**
 * ［指定日の勤怠登録］サービス
 */
public class DailyWorkedTimeRegistrationService {

    @Value
    @AllArgsConstructor(staticName="of")
    public static class Parameter {

        final AttendanceDate attendanceDate;
        final WorkBeginTime beginTime;
        final WorkEndTime endTime;
    }

    final WorkedTimeRepository repository;


    public DailyWorkedTimeRegistrationService(WorkedTimeRepository repository) {
        this.repository = repository;
    }


    public void execute(Parameter parameter) {
        _registerWorkTime(parameter);
    }

    private void _registerWorkTime(Parameter parameter) {

        DailyTimeRecord fact = DailyTimeRecord.of(
                parameter.attendanceDate,
                parameter.beginTime,
                parameter.endTime);
        DailyWorkedTime record = fact.calculateDetailedWorkTime();
        repository.save(record);
    }

}

package com.naosim.dddwork.kintai.datasource.csv.repository;

import com.naosim.dddwork.kintai.datasource.csv.dao.RegulatedBreakTimeCsvDao;
import com.naosim.dddwork.kintai.datasource.csv.dao.RegulatedWorkingTimeMinutesCsvDao;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedBreakTimeEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedWorkingTimeMinutesEntity;
import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import com.naosim.dddwork.kintai.service.RegulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Repository
@RequiredArgsConstructor
public class RegulationRepositoryCsv implements RegulationRepository {
    private final RegulatedBreakTimeCsvDao regulatedBreakTimeCsvDao;
    private final RegulatedWorkingTimeMinutesCsvDao regulatedWorkingTimeMinutesCsvDao;

    @Override
    public RegulatedBreakTimeShift fetchBreakTimeShift(AttendanceDate attendanceDate) throws Exception {
        RegulatedBreakTimeEntities records = regulatedBreakTimeCsvDao.fetchAll();

        // TODO: マスタの有効開始終了チェック
        List<RegulatedBreakTimeInterval> regulatedBreakTimeIntervalList = records.getRecords()
                .stream()
                .map(regulatedBreakTimeEntity -> new RegulatedBreakTimeInterval(
                        new TimeInterval(
                                new StartTime(attendanceDate.toLocalDate(), regulatedBreakTimeEntity.getBreakTimeStart()),
                                new EndTime(attendanceDate.toLocalDate(), regulatedBreakTimeEntity.getBreakTimeEnd())
                        )))
                .collect(Collectors.toList());

        return new RegulatedBreakTimeShift(regulatedBreakTimeIntervalList);
    }

    @Override
    public RegulatedWorkingTimeMinutes fetchRegulatedWorkingTimeMinutes(AttendanceDate attendanceDate) throws Exception {
        RegulatedWorkingTimeMinutesEntity record = regulatedWorkingTimeMinutesCsvDao.fetch();
        return new RegulatedWorkingTimeMinutes(new TimeLength(record.getRegulatedWorkingTimeMinutes(), MINUTES));
    }

    @Override
    public RegulatedWorkingTimeInterval fetchRegulatedWorkingTimeInterval(AttendanceDate attendanceDate) {
        // TODO: CSVから値を取得する
        return new RegulatedWorkingTimeInterval(
                new TimeInterval(
                        new StartTime(attendanceDate.toLocalDate(), LocalTime.of(9, 0)),
                        new EndTime(attendanceDate.toLocalDate(), LocalTime.of(18, 0))
                ));
    }
}

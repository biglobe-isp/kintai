package com.naosim.dddwork.kintai.datasource.csv.repository;

import com.naosim.dddwork.kintai.datasource.csv.dao.RegulatedBreakTimeCsvDao;
import com.naosim.dddwork.kintai.datasource.csv.dao.RegulatedWorkingTimeMinutesCsvDao;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedBreakTimeEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedBreakTimeEntity;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedWorkingTimeMinutesEntity;
import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import com.naosim.dddwork.kintai.service.RegulationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Repository
public class RegulationRepositoryCsv implements RegulationRepository {

    @Override
    public RegulatedBreakTimeShift fetchBreakTimeShift(AttendanceDate attendanceDate) {
        RegulatedBreakTimeCsvDao regulatedBreakTimeCsvDao = new RegulatedBreakTimeCsvDao();
        RegulatedBreakTimeEntities records = regulatedBreakTimeCsvDao.fetchAll();
        List<RegulatedBreakTimeInterval> regulatedBreakTimeIntervalList = new ArrayList<>();
        // HACK: for文やめたい
        for (RegulatedBreakTimeEntity entity : records.getRecords()) {
            // TODO: マスタの有効開始終了チェック
            RegulatedBreakTimeInterval interval = null;
            try {
                interval = new RegulatedBreakTimeInterval(
                        new TimeInterval(
                            new StartTime(attendanceDate.getZonedDateTime(), entity.getBreakTimeStart()),
                            new EndTime(attendanceDate.getZonedDateTime(), entity.getBreakTimeEnd())
                        )
                );
                regulatedBreakTimeIntervalList.add(interval);
            } catch (Exception e) {
                // TODO: エラーハンドリング
                e.printStackTrace();
            }
        }
        return new RegulatedBreakTimeShift(regulatedBreakTimeIntervalList);
    }

    @Override
    public RegulatedWorkingTimeMinutes fetchRegulatedWorkingTimeMinutes(AttendanceDate attendanceDate) {
        RegulatedWorkingTimeMinutesCsvDao regulatedWorkingTimeMinutesCsvDao = new RegulatedWorkingTimeMinutesCsvDao();
        RegulatedWorkingTimeMinutesEntity record = regulatedWorkingTimeMinutesCsvDao.fetch();
        return new RegulatedWorkingTimeMinutes(new TimeLength(record.getRegulatedWorkingTimeMinutes(), MINUTES));
    }
}

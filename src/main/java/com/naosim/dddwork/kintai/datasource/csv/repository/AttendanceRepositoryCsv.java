package com.naosim.dddwork.kintai.datasource.csv.repository;

import com.naosim.dddwork.kintai.datasource.csv.dao.AttendanceCsvDao;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntity;
import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceAggregationMonthly;
import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;
import com.naosim.dddwork.kintai.service.AttendanceRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class AttendanceRepositoryCsv implements AttendanceRepository {

    @Override
    public void register(AttendanceRecord attendanceRecord) {
        Object lockObj = new Object();
        AttendanceCsvDao attendanceCsvDao = new AttendanceCsvDao();
        synchronized (lockObj) {
            // TODO: リファクタ
            AttendanceRecordEntities records = new AttendanceRecordEntities(new ArrayList<>());
            records.add(attendanceRecord);
            attendanceCsvDao.register(records);
        }
    }

    @Override
    public AttendanceAggregationMonthly fetchMonthly(AggregationMonth aggregationMonth) {
        AttendanceCsvDao attendanceCsvDao = new AttendanceCsvDao();
        AttendanceRecordEntities records = attendanceCsvDao.fetchMonthly(aggregationMonth);
        return new AttendanceAggregationMonthly(
                records.getRecords().stream().map(AttendanceRecordEntity::getActualWorkingTimeMinutes).reduce(Integer::sum),
                records.getRecords().stream().map(AttendanceRecordEntity::getActualOvertimeMinutes).reduce(Integer::sum)
        );
    }
}

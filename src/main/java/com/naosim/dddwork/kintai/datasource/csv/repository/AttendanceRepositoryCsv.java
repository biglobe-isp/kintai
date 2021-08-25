package com.naosim.dddwork.kintai.datasource.csv.repository;

import com.naosim.dddwork.kintai.datasource.csv.dao.AttendanceCsvDao;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntity;
import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceAggregationMonthly;
import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;
import com.naosim.dddwork.kintai.service.AttendanceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceRepositoryCsv implements AttendanceRepository {

    @Override
    public void register(AttendanceRecord attendanceRecord) {
        Object lockObj = new Object();
        AttendanceCsvDao attendanceCsvDao = new AttendanceCsvDao();
        synchronized (lockObj) {
            AttendanceRecordEntities records = attendanceCsvDao.fetchAll();
            records.upsert(attendanceRecord);
            attendanceCsvDao.register(records);
        }
    }
}

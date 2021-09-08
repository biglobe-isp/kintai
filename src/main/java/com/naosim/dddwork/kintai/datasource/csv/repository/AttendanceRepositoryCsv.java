package com.naosim.dddwork.kintai.datasource.csv.repository;

import com.naosim.dddwork.kintai.datasource.csv.dao.AttendanceCsvDao;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntity;
import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecords;
import com.naosim.dddwork.kintai.service.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final AttendanceCsvDao attendanceCsvDao;

    @Override
    public void register(AttendanceRecord attendanceRecord) throws Exception {
        AttendanceRecordEntities records = new AttendanceRecordEntities(new ArrayList<>());
        records.add(attendanceRecord);
        attendanceCsvDao.register(records);
    }

    @Override
    public AttendanceRecords fetchMonthly(AggregationMonth aggregationMonth) throws Exception {
        AttendanceRecordEntities records = attendanceCsvDao.fetchMonthly(aggregationMonth);
        List<AttendanceRecord> recordList = records.getRecords().stream()
                .map(AttendanceRecordEntity::toDomain)
                .collect(Collectors.toList());
        return new AttendanceRecords(recordList);
    }
}

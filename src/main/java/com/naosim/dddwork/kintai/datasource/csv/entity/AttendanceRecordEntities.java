package com.naosim.dddwork.kintai.datasource.csv.entity;

import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Value
public class AttendanceRecordEntities {
    List<AttendanceRecordEntity> records;

    public AttendanceRecordEntities(List<AttendanceRecordEntity> records) {
        if (records == null) {
            throw new IllegalArgumentException("出勤記録リストがありません。");
        }
        this.records = records;
    }

    public void upsert(AttendanceRecord record) {
        Optional<Integer> index = this.indexOf(record);
        if (index.isPresent()) {
            update(record, index.get());
        } else {
            add(record);
        }
    }

    // TODO: リファクタ
    public void update(AttendanceRecord record, int index) {
        records.get(index).setStartTime(record.getStartTime());
        records.get(index).setEndTime(record.getEndTime());
        records.get(index).setWorkingTimeMinutes(record.getWorkingTimeMinutes());
        records.get(index).setOvertimeMinutes(record.getOvertimeMinutes());
        records.get(index).setUpdatedAt(ZonedDateTime.now());
    }

    // TODO: リファクタ
    public void add(AttendanceRecord record) {
        AttendanceRecordEntity newRecord = new AttendanceRecordEntity();
        newRecord.setYmd(record.getYmd());
        newRecord.setStartTime(record.getStartTime());
        newRecord.setEndTime(record.getEndTime());
        newRecord.setWorkingTimeMinutes(record.getWorkingTimeMinutes());
        newRecord.setOvertimeMinutes(record.getOvertimeMinutes());
        newRecord.setUpdatedAt(ZonedDateTime.now());
        records.add(newRecord);
    }

    public Optional<Integer> indexOf(AttendanceRecord record) {
        AtomicInteger atomicInt = new AtomicInteger(-1);
        return records
                .stream()
                .peek(rec -> atomicInt.incrementAndGet())
                .filter(rec -> record.getYmd().equals(rec.getYmd()))
                .findFirst()
                .map(rec -> atomicInt.get());
    }
}

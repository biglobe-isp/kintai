package com.naosim.dddwork.kintai.datasource.csv.entity;

import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.List;

@Value
public class AttendanceRecordEntities {
    List<AttendanceRecordEntity> records;

    public AttendanceRecordEntities(List<AttendanceRecordEntity> records) {
        if (records == null) {
            throw new IllegalArgumentException("出勤記録リストがありません。");
        }
        this.records = records;
    }

    // TODO: リファクタ
    public void add(AttendanceRecord record) {
        AttendanceRecordEntity newRecord = new AttendanceRecordEntity();
        newRecord.setAttendanceDate(record.getAttendanceLocalDate());
        newRecord.setStartTime(record.getAttendanceStartLocalTime());
        newRecord.setEndTime(record.getAttendanceEndLocalTime());
        newRecord.setActualWorkingTimeMinutes(record.getActualWorkingTimeMinutes().intValue());
        newRecord.setActualOvertimeMinutes(record.getActualOvertimeMinutes().intValue());
        newRecord.setUpdatedAt(ZonedDateTime.now());
        records.add(newRecord);
    }

//    public void upsert(AttendanceRecord record) {
//        Optional<Integer> index = this.indexOf(record);
//        if (index.isPresent()) {
//            update(record, index.get());
//        } else {
//            add(record);
//        }
//    }

    // TODO: リファクタ
//    public void update(AttendanceRecord record, int index) {
//        records.get(index).setStartTime(record.getAttendanceStartLocalTime());
//        records.get(index).setEndTime(record.getAttendanceEndLocalTime());
//        records.get(index).setWorkingTimeMinutes(record.getActualWorkingTimeMinutes().intValue());
//        records.get(index).setOvertimeMinutes(record.getActualWorkingTimeMinutes().intValue());
//        records.get(index).setUpdatedAt(ZonedDateTime.now());
//    }


//    public Optional<Integer> indexOf(AttendanceRecord record) {
//        AtomicInteger atomicInt = new AtomicInteger(-1);
//        return records
//                .stream()
//                .peek(rec -> atomicInt.incrementAndGet())
//                .filter(rec -> record.getAttendanceLocalDate().equals(rec.getYmd()))
//                .findFirst()
//                .map(rec -> atomicInt.get());
//    }
}

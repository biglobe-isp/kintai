package com.naosim.dddwork.kintai.datasource.timerecord.csv.domain.builder;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkBeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkEndTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyTimeRecord;

import static com.naosim.dddwork.kintai.datasource.timerecord.csv.domain.builder.DailyTimeRecordBuilder.CsvColumn.ATTENDANCE_DATE;
import static com.naosim.dddwork.kintai.datasource.timerecord.csv.domain.builder.DailyTimeRecordBuilder.CsvColumn.WORK_BEGIN_TIME;
import static com.naosim.dddwork.kintai.datasource.timerecord.csv.domain.builder.DailyTimeRecordBuilder.CsvColumn.WORK_END_TIME;


/**
 * ビルダー for DailyTimeRecord
 */
public class DailyTimeRecordBuilder {

    enum CsvColumn {

        ATTENDANCE_DATE(0),
        WORK_BEGIN_TIME(1),
        WORK_END_TIME(2),
        TOTAL_WORK_MINUTES(3),
        OVERTIME_MINUTES(4),
        RECORDED_TIMESTAMP(5),
        ;

        private final int _index;

        CsvColumn(int index) {
            _index = index;
        }

        private int index() {
            return _index;
        }
    }

    public static DailyTimeRecord fromCsv(String csv) {

        final String[] columns = csv.split(",");

        final AttendanceDate attendanceDate = AttendanceDate.of(columns[ATTENDANCE_DATE.index()]);
        final WorkBeginTime workBeginTime = WorkBeginTime.of(columns[WORK_BEGIN_TIME.index()]);
        final WorkEndTime workEndTime = WorkEndTime.of(columns[WORK_END_TIME.index()]);

//NOTE: 実は記録した合計値は使っていないのだ...
//        final AmountOfMinutes totalWorkMinutes = AmountOfMinutes.of(Integer.valueOf(columns[TOTAL_WORK_MINUTES.index()]));
//        final AmountOfMinutes overtimeMinutes = AmountOfMinutes.of(Integer.valueOf(columns[OVERTIME_MINUTES.index()]));
//NOTE: 同一日付の最新を採取するのに、実は記録したタイムスタンプは使っていないのだ...
//        final RecordTimestamp recordedTimestamp = columns[RECORDED_TIMESTAMP.index()];

        return DailyTimeRecord.of(attendanceDate, workBeginTime, workEndTime);
    }

}

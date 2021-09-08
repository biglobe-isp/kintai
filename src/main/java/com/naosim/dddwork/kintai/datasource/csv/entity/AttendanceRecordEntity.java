package com.naosim.dddwork.kintai.datasource.csv.entity;

import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.ActualMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.overtime.ActualOvertimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.workingtime.ActualWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Data
public class AttendanceRecordEntity {
    @CsvDate("yyyy-MM-dd")
    @CsvBindByPosition(position = 0)
    private LocalDate attendanceDate;

    @CsvDate("HH:mm")
    @CsvBindByPosition(position = 1)
    private LocalTime startTime;

    @CsvDate("HH:mm")
    @CsvBindByPosition(position = 2)
    private LocalTime endTime;

    @CsvBindByPosition(position = 3)
    private int actualWorkingTimeMinutes;

    @CsvBindByPosition(position = 4)
    private int actualOvertimeMinutes;

    @CsvDate("yyyy-MM-dd'T'HH:mm:ss.SSSXXX'['VV']'")
    @CsvBindByPosition(position = 5)
    private ZonedDateTime createdAt;

    public AttendanceRecordEntity() {}

    protected AttendanceRecordEntity(LocalDate attendanceDate, LocalTime startTime, LocalTime endTime, int actualWorkingTimeMinutes, int actualOvertimeMinutes) {
        this.attendanceDate = attendanceDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.actualWorkingTimeMinutes = actualWorkingTimeMinutes;
        this.actualOvertimeMinutes = actualOvertimeMinutes;
        this.createdAt = ZonedDateTime.now();
    }
    
    public AttendanceRecord toDomain() {
        return new AttendanceRecord(
                new AttendanceDate(this.getAttendanceDate()),
                new AttendanceTimeInterval(
                        new TimeInterval(
                                new StartTime(this.getAttendanceDate(), this.getStartTime()),
                                new EndTime(this.getAttendanceDate(), this.getEndTime())
                        )
                ),
                new ActualMinutes(
                        new ActualWorkingTimeMinutes(new TimeLength(this.getActualWorkingTimeMinutes(), MINUTES)),
                        new ActualOvertimeMinutes(new TimeLength(this.getActualOvertimeMinutes(), MINUTES))
                )
        );
    }

}

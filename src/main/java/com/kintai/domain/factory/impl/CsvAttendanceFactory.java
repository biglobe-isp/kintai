package com.kintai.domain.factory.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.value.*;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.exception.ValidatorException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvAttendanceFactory implements IAttendanceFactory {
    @Override
    public Attendance makeAttendance(AttendanceFactoryDto attendanceFactoryDto) throws ValidatorException {
        TotalMonth totalMonth = new TotalMonth(attendanceFactoryDto.getTotalMonth());
        WorkDate workDate = new WorkDate(attendanceFactoryDto.getWorkDate());
        WorkTime workTime = new WorkTime(attendanceFactoryDto.getStartTime(), attendanceFactoryDto.getEndTime());
        WorkMinutes workMinutes = new WorkMinutes(Integer.parseInt(attendanceFactoryDto.getWorkMinutes()));
        OverWorkMinutes overWorkMinutes = new OverWorkMinutes(Integer.parseInt(attendanceFactoryDto.getOverWorkMinute()));
        LocalDateTime localDateTime = LocalDateTime.parse(attendanceFactoryDto.getLocalDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        return new Attendance(totalMonth, workDate, workTime, workMinutes, overWorkMinutes, localDateTime);
    }
}

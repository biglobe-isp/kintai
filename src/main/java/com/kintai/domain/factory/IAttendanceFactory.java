package com.kintai.domain.factory;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.exception.ValidatorException;

import java.text.ParseException;

public interface IAttendanceFactory {
    Attendance makeAttendance(AttendanceFactoryDto attendanceFactoryDto) throws ValidatorException, ParseException;
}

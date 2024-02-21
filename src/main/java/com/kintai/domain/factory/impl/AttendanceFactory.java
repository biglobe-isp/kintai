package com.kintai.domain.factory.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.value.*;
import com.kintai.datasource.value.expansion.endtime.EndTimeExtend;
import com.kintai.datasource.value.expansion.starttime.StartTimeExtend;
import com.kintai.datasource.value.expansion.workdate.WorkDateExtend;
import com.kintai.datasource.value.expansion.workminutes.WorkMinutesExtend;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.exception.ValidatorException;

import java.text.ParseException;
import java.time.LocalDateTime;

public class AttendanceFactory implements IAttendanceFactory {
    @Override
    public Attendance makeAttendance(AttendanceFactoryDto attendanceFactoryDto) throws ValidatorException, ParseException {
        WorkDate workDate = new WorkDateExtend(attendanceFactoryDto.getWorkDate());
        TotalMonth totalMonth = new TotalMonth(workDate);
        StartTime startTime = new StartTimeExtend(attendanceFactoryDto.getStartTime());
        EndTime endTime = new EndTimeExtend(attendanceFactoryDto.getEndTime());
        WorkTime workTime = new WorkTime(startTime, endTime);
        WorkMinutes workMinutes = new WorkMinutesExtend(workTime, workDate);
        OverWorkMinutes overWorkMinutes = new OverWorkMinutes(workMinutes);
        return new Attendance(totalMonth, workDate, workTime, workMinutes, overWorkMinutes, LocalDateTime.now());
    }
}

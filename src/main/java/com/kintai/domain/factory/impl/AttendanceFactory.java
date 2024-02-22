package com.kintai.domain.factory.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.value.*;
import com.kintai.datasource.value.expansion.endtime.EndTimeExtend;
import com.kintai.datasource.value.expansion.overworkminutes.OverWorkMinutesExtend;
import com.kintai.datasource.value.expansion.starttime.StartTimeExtend;
import com.kintai.datasource.value.expansion.workdate.WorkDateExtend;
import com.kintai.datasource.value.expansion.workminutes.WorkMinutesExtend;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.exception.ValidatorException;

import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * 勤怠エンティティを生成するファクトリーインターフェース{@link IAttendanceFactory}を実装したクラス
 */
public class AttendanceFactory implements IAttendanceFactory {
    /**
     * 勤怠エンティティを生成します。
     * 勤怠エンティティに値を格納するために、DTOの各値をValue Objectに設定。
     * @param attendanceFactoryDto 勤怠エンティティの生成に必要なデータを格納しているDTOクラス
     * @return 勤怠エンティティ
     * @throws ValidatorException 勤怠管理の値で異常を検知した場合、{@link ValidatorException}をスローします。
     * @throws ParseException 勤怠管理の値で異常を検知した場合、{@link ParseException}をスローします。
     */
    @Override
    public Attendance makeAttendance(AttendanceFactoryDto attendanceFactoryDto) throws ValidatorException, ParseException {
        WorkDate workDate = new WorkDateExtend(attendanceFactoryDto.getWorkDate());
        TotalMonth totalMonth = new TotalMonth(workDate);
        StartTime startTime = new StartTimeExtend(attendanceFactoryDto.getStartTime());
        EndTime endTime = new EndTimeExtend(attendanceFactoryDto.getEndTime());
        WorkTime workTime = new WorkTime(startTime, endTime);
        WorkMinutes workMinutes = new WorkMinutesExtend(workTime, workDate);
        OverWorkMinutes overWorkMinutes = new OverWorkMinutesExtend(workMinutes, workDate);
        return new Attendance(totalMonth, workDate, workTime, workMinutes, overWorkMinutes, LocalDateTime.now());
    }
}

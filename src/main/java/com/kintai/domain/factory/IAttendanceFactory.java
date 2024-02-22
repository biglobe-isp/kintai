package com.kintai.domain.factory;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.exception.ValidatorException;

import java.text.ParseException;

/**
 * 勤怠エンティティを生成するファクトリーインターフェース
 */
public interface IAttendanceFactory {
    /**
     * 勤怠エンティティを生成します。
     * @param attendanceFactoryDto 勤怠エンティティの生成に必要なデータを格納しているDTOクラス
     * @return 勤怠エンティティ
     * @throws ValidatorException 勤怠管理の値で異常を検知した場合、{@link ValidatorException}をスローします。
     * @throws ParseException 勤怠管理の値で異常を検知した場合、{@link ParseException}をスローします。
     */
    Attendance makeAttendance(AttendanceFactoryDto attendanceFactoryDto) throws ValidatorException, ParseException;
}

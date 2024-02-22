package com.kintai.domain.factory.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.value.*;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.exception.ValidatorException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 勤怠エンティティを生成するファクトリーインターフェースを実装したクラス
 * CSVファイルから読み込んだ勤怠データを用いて勤怠エンティティを生成する際に使用します。
 */
public class CsvAttendanceFactory implements IAttendanceFactory {
    /**
     * 勤怠エンティティを生成します。
     * CSVファイルから読み込んだ勤怠データをもとに、DTOの各値をValue Objectに設定。
     * 勤務日や始業時刻、終業時刻は勤怠登録時と値が異なるため、拡張前のValue Objectを使用。
     * @param attendanceFactoryDto 勤怠エンティティの生成に必要なデータを格納しているDTOクラス
     * @return 勤怠エンティティ
     * @throws ValidatorException 勤怠管理の値で異常を検知した場合、{@link ValidatorException}をスローします。
     */
    @Override
    public Attendance makeAttendance(AttendanceFactoryDto attendanceFactoryDto) throws ValidatorException {
        TotalMonth totalMonth = new TotalMonth(attendanceFactoryDto.getTotalMonth());
        WorkDate workDate = new WorkDate(attendanceFactoryDto.getWorkDate());
        WorkTime workTime = new WorkTime(new StartTime(attendanceFactoryDto.getStartTime()), new EndTime(attendanceFactoryDto.getEndTime()));
        WorkMinutes workMinutes = new WorkMinutes(Integer.parseInt(attendanceFactoryDto.getWorkMinutes()));
        OverWorkMinutes overWorkMinutes = new OverWorkMinutes(Integer.parseInt(attendanceFactoryDto.getOverWorkMinute()));
        LocalDateTime localDateTime = LocalDateTime.parse(attendanceFactoryDto.getLocalDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        return new Attendance(totalMonth, workDate, workTime, workMinutes, overWorkMinutes, localDateTime);
    }
}

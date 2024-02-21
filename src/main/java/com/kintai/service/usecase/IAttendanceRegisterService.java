package com.kintai.service.usecase;

import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;

/**
 * 勤怠登録ユースケースサービスのインターフェース
 */
public interface IAttendanceRegisterService {
    /**
     * 勤怠登録メソッド
     * @param requestAttendanceRegisterDto 勤怠登録リクエストDTO
     * @return 勤怠登録レスポンスDTO
     */
    ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto);
}

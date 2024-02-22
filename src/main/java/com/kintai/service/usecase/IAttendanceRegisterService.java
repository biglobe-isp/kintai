package com.kintai.service.usecase;

import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;

/**
 * 勤怠データを登録するユースケースサービスインターフェース
 */
public interface IAttendanceRegisterService {
    /**
     * 勤怠データを登録します。
     * @param requestAttendanceRegisterDto 勤怠データの登録に必要データを格納しているDTO
     * @return 勤怠を登録した結果を格納するDTO
     */
    ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto);
}

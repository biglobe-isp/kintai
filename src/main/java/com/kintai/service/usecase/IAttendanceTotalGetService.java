package com.kintai.service.usecase;

import com.kintai.service.dto.request.RequestAttendanceGetDto;
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto;

/**
 * 集計した勤怠データを取得するユースケースサービスインターフェース
 */
public interface IAttendanceTotalGetService {
    /**
     * 集計した勤怠データを集計します。
     * @return 集計した勤怠データを格納するDTO
     */
    ResponseAttendanceTotalGetDto get();

    /**
     * 引数のDTOを元に勤怠データを集計します。
     * @param requestAttendanceGetV1Dto 勤怠データの集計に必要なデータを格納しているDTO
     * @return 集計した勤怠データを格納するDTO
     */
    ResponseAttendanceTotalGetDto get(RequestAttendanceGetDto requestAttendanceGetV1Dto);

}

package com.kintai.service.dto.request;

import lombok.Getter;

/**
 * 勤怠登録リクエストDTO
 */
public class RequestAttendanceRegisterDto {
    /* 勤怠日 */
    @Getter
    private final String workDate;

    /* 開始時刻 */
    @Getter
    private final String startTime;

    /* 終業時刻 */
    @Getter
    private final String endTime;

    public RequestAttendanceRegisterDto(String workDate, String startTime, String endTime) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "勤務日：" + workDate + " 開始時刻：" + startTime + " 終業時刻：" + endTime;
    }
}

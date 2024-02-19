package com.kintai.domain.dto;

import lombok.Data;

@Data
public class AttendanceFactoryDto {
    /* 勤怠日 */
    private String workDate;

    /* 開始時刻 */
    private String startTime;

    /* 終業時刻 */
    private String endTime;

    /* 勤務月 */
    private String totalMonth;

    /* 労働時間 */
    private String workMinutes;

    /* 残業時間 */
    private String overWorkMinute;

    /* 登録日 */
    private String localDateTime;

    public AttendanceFactoryDto(String workDate, String startTime, String endTime) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

package com.kintai.service.dto.request;

import lombok.Getter;

/**
 * 勤怠登録リクエストDTO
 */
public class RequestAttendanceRegisterDto {
    /* 勤怠日 */
    @Getter
    private final String workDate;

    /* 始業時刻 */
    @Getter
    private final String startTime;

    /* 終業時刻 */
    @Getter
    private final String endTime;

    /* パスワード */
    @Getter
    private String password;

    /**
     * コンストラクタ
     * @param workDate 勤怠日
     * @param startTime 始業時刻
     * @param endTime 終業時刻
     */
    public RequestAttendanceRegisterDto(String workDate, String startTime, String endTime) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * コンストラクタ
     * @param workDate 勤怠日
     * @param startTime 始業時刻
     * @param endTime　終業時刻
     * @param password DBパスワード
     */
    public RequestAttendanceRegisterDto(String workDate, String startTime, String endTime, String password) {
        this(workDate,startTime,endTime);
        this.password = password;
    }

    /**
     * String変換メソッド
     * @return DTOに入力された値を一つの文字列として返却　※パスワードはセキュリティ上出力しないものとする
     */
    @Override
    public String toString() {
        return "勤務日：" + workDate + " 開始時刻：" + startTime + " 終業時刻：" + endTime;
    }
}

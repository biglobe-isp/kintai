package com.kintai.service.dto.response;


import lombok.Getter;
import lombok.Setter;

/**
 * 勤怠登録レスポンスDTO
 */
public class ResponseAttendanceRegisterDto {
    /* 勤怠日 */
    @Getter
    private final String workDate;

    /* 開始時刻 */
    @Getter
    private final String startTime;

    /* 終業時刻 */
    @Getter
    private final String endTime;

    /* 処理結果メッセージ */
    @Getter
    private String resultMessage;

    /**
     * コンストラクタ
     * @param workDate 勤務日
     * @param startTime 始業時刻
     * @param endTime 終業時刻
     * @param resultMessage 処理結果メッセージ
     */
    public ResponseAttendanceRegisterDto(String workDate, String startTime, String endTime,String resultMessage) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.resultMessage = resultMessage;
    }

    /**
     * String変換メソッド
     * @return DTOに入力された値を一つの文字列として返却
     */
    @Override
    public String toString() {
        return "勤務日：" + workDate + " 開始時刻：" + startTime + " 終業時刻：" + endTime + " 処理結果メッセージ：" + resultMessage;
    }
}

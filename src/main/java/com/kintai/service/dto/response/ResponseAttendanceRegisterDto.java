package com.kintai.service.dto.response;


import lombok.Getter;

/**
 * 勤怠データの登録後に必要データを格納するDTO
 * ユースケースからAPI宛にデータを連携する際に使用します。
 */
public class ResponseAttendanceRegisterDto {
    // 勤怠日
    @Getter
    private final String workDate;

    // 開始時刻
    @Getter
    private final String startTime;

    // 終業時刻
    @Getter
    private final String endTime;

    // 処理結果メッセージ
    @Getter
    private final String resultMessage;

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
     * @return DTOに入力された値を一つの文字列として返却します。
     */
    @Override
    public String toString() {
        return "勤務日：" + workDate + " 開始時刻：" + startTime + " 終業時刻：" + endTime + " 処理結果メッセージ：" + resultMessage;
    }
}

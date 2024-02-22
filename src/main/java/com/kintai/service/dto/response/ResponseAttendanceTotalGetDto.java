package com.kintai.service.dto.response;

import com.kintai.datasource.entity.WorkTotal;

import java.util.List;

/**
 * 勤怠データの取得後に必要データを格納するDTO
 * ユースケースからAPI宛にデータを連携する際に使用します。
 */
public class ResponseAttendanceTotalGetDto {
    // 労働集計データリスト
    private final List<WorkTotal> workTotalList;

    // 処理結果メッセージ
    private final String resultMessage;

    /**
     * コンストラクタ
     * @param workTotalList 労働集計データリスト
     * @param resultMessage 処理結果メッセージ
     */
    public ResponseAttendanceTotalGetDto(List<WorkTotal> workTotalList, String resultMessage) {
        this.workTotalList = workTotalList;
        this.resultMessage = resultMessage;
    }

    /**
     * String変換メソッド
     * 労働集計データリストにデータがある場合はカンマ区切りで労働集計データを文字列化します。
     * 複数県ある場合は末尾改行を入れます。
     * @return DTOに入力された値を一つの文字列として返却します。
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(resultMessage).append(System.lineSeparator());
        String comma = ",";
        for (WorkTotal workTotal : workTotalList) {
            builder.append("集計月：").append(workTotal.getTotalMonth().getTotalMonth()).append(comma);
            builder.append("勤務時間：").append(workTotal.getWorkMinutes().getWorkMinutes()).append(comma);
            builder.append("残業時間：").append(workTotal.getOverWorkMinutes().getOverWorkMinutes());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}

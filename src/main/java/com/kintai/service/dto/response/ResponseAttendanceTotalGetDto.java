package com.kintai.service.dto.response;

import com.kintai.datasource.entity.Total;

import java.util.List;

public class ResponseAttendanceTotalGetDto {
    /* 集計済み勤怠表 */
    private final List<Total> totalList;

    /* 処理結果メッセージ */
    private final String resultMessage;

    public ResponseAttendanceTotalGetDto(List<Total> totalList, String resultMessage) {
        this.totalList = totalList;
        this.resultMessage = resultMessage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(resultMessage).append(System.lineSeparator());
        String comma = ",";
        for (Total total : totalList) {
            builder.append("集計月：").append(total.getTotalMonth().getTotalMonth()).append(comma);
            builder.append("勤務時間：").append(total.getWorkMinutes().getWorkMinutes()).append(comma);
            builder.append("残業時間：").append(total.getOverWorkMinutes().getOverWorkMinutes());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}

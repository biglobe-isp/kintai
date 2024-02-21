package com.kintai.service.dto.response;

import com.kintai.datasource.entity.WorkTotal;

import java.util.List;

public class ResponseAttendanceTotalGetDto {
    /* 集計済み勤怠表 */
    private final List<WorkTotal> workTotalList;

    /* 処理結果メッセージ */
    private final String resultMessage;

    public ResponseAttendanceTotalGetDto(List<WorkTotal> workTotalList, String resultMessage) {
        this.workTotalList = workTotalList;
        this.resultMessage = resultMessage;
    }

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

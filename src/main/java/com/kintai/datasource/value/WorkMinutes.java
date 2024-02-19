package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 労働時間のvalueオブジェクト
 */
public class WorkMinutes {
    @Getter
    protected int workMinutes;

    public WorkMinutes(){}

    public WorkMinutes(int workMinutes) {
        this.workMinutes = workMinutes;
    }

    public WorkMinutes(WorkTime workTime) {
        this.workMinutes = calculateWorkMinutes(workTime.getStartTime().getStartTime(), workTime.getEndTime().getEndTime());
    }

    public WorkMinutes(List<WorkMinutes> workMinutesList) {
        this.workMinutes = workMinutesList.stream().mapToInt(workMinutes -> workMinutes.getWorkMinutes()).sum();
    }

    /**
     * 労働時間を計算する。労働時間は分単位で出力
     * 休憩時間が「12:00-13:00」, 「18:00-19:00」, 「21:00-22:00」のため終業時刻が13時、19時、22時の場合は1時間分を休憩時間として勤務時間から削減する
     * @return 労働時間
     */
    protected int calculateWorkMinutes(String startTime, String endTime) {
        int startH = parseInt(startTime.substring(0, 2));
        int startM = parseInt(startTime.substring(2, 4));

        int endH = parseInt(endTime.substring(0, 2));
        int endM = parseInt(endTime.substring(2, 4));
        int calculateWorkTimeValue = endH * 60 + endM - (startH * 60 + startM);

        if (endH == 12) {
            calculateWorkTimeValue -= endM;
        } else if (endH >= 13) {
            calculateWorkTimeValue -= 60;
        }

        if (endH == 18) {
            calculateWorkTimeValue -= endM;
        } else if (endH >= 19) {
            calculateWorkTimeValue -= 60;
        }

        if (endH == 21) {
            calculateWorkTimeValue -= endM;
        } else if (endH >= 22) {
            calculateWorkTimeValue -= 60;
        }

        return calculateWorkTimeValue;
    }

    protected int parseInt(String time) {
        return Integer.parseInt(time);
    }

}

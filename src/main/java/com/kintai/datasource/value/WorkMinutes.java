package com.kintai.datasource.value;

import lombok.Getter;
import java.util.List;

/**
 * 勤怠管理として労働時間を管理するValue Object
 * 労働時間は分単位とします
 */
public class WorkMinutes {
    // 労働時間の値
    @Getter
    protected int workMinutes;

    /**
     * デフォルトコンストラクタ
     */
    public WorkMinutes(){}

    /**
     * コンストラクタ
     * @param workMinutes 労働時間
     */
    public WorkMinutes(int workMinutes) {
        this.workMinutes = workMinutes;
    }

    /**
     * 労働時間の計算まで行うコンストラクタ
     * 労働時間は引数の労働時刻{@link WorkTime}の始業時刻{@link StartTime}と終業時刻{@link EndTime}を使用して算出します。
     * @param workTime 労働時刻
     */
    public WorkMinutes(WorkTime workTime) {
        this.workMinutes = calculateWorkMinutes(workTime.getStartTime().getStartTime(), workTime.getEndTime().getEndTime());
    }

    /**
     * 労働時間の合計を算出したコンストラクタ
     * 引数の労働時間リストを算出して労働時間の合計値を算出します。
     * @param workMinutesList 合計値を算出する労働時間リスト
     */
    public WorkMinutes(List<WorkMinutes> workMinutesList) {
        this.workMinutes = workMinutesList.stream().mapToInt(WorkMinutes::getWorkMinutes).sum();
    }

    /**
     * 労働時間を計算します。
     * 終業時刻から始業時刻を差し引いた値を労働時間として計算します。
     * 休憩時間が「12:00-13:00」, 「18:00-19:00」, 「21:00-22:00」のため終業時刻が13時、19時、22時の場合は1時間分を休憩時間として労働時間から削減します。
     * 終業時間が休憩時間内の場合は、分数分を休憩時間として労働時間から差し引きます。例：終業時刻が12:30の場合、30分を休憩時間として労働時間から差し引く。
     * @param startTime 始業時刻
     * @param endTime 終業時刻
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

    /**
     * 労働時間をStringからIntに変換します。
     * @param time 時刻
     * @return int型の時刻
     */
    protected int parseInt(String time) {
        return Integer.parseInt(time);
    }

}

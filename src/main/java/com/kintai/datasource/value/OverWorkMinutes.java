package com.kintai.datasource.value;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 勤怠管理として残業時間を管理するValue Object
 * 残業時間は分単位とします。
 */
public class OverWorkMinutes {
    /*
    * 残業時間の値
    * 残業時間は分単位で表します。
    */
    @Getter
    protected int overWorkMinutes;

    // 1日の労働基準時間(分)
    @Setter
    protected int baseWorkMinutes = 480;

    /**
     * デフォルトコンストラクタ
     * @param overWorkMinutes 残業時間
     */
    public OverWorkMinutes(int overWorkMinutes) {
        this.overWorkMinutes = overWorkMinutes;
    }

    /**
     * 残業時間の計算まで行うコンストラクタ
     * 残業時間は引数の労働時間{@link WorkMinutes}を使用して算出します。
     * @param workMinutes 労働時間
     */
    public OverWorkMinutes(WorkMinutes workMinutes) {
        this.overWorkMinutes = calculateOverWorkMinutes(workMinutes.getWorkMinutes());
    }

    /**
     * 残業時間の合計を算出したコンストラクタ
     * 引数の残業時間リストを使用して残業時間の合計値を算出します。
     * @param overWorkMinutesList 残業時間リスト
     */
    public OverWorkMinutes(List<OverWorkMinutes> overWorkMinutesList) {
        this.overWorkMinutes = sumOverWorkMinutes(overWorkMinutesList);
    }

    /**
     * 残業時間を計算します。
     * 労働時間を労働基準時間で差し引きます。
     * @param workMinutes 労働時間
     * @return 計算した結果、値がプラスの場合はその値を残業時間と設定。0またはマイナスの場合は残業時間を0と設定します。
     */
    protected int calculateOverWorkMinutes(int workMinutes) {
        return Math.max(workMinutes - baseWorkMinutes, 0);
    }

    /**
     * 残業時間の合計値を計算します。
     * @param overWorkMinutesList 残業時間リスト
     * @return 残業時間の合計値
     */
    protected int sumOverWorkMinutes(List<OverWorkMinutes> overWorkMinutesList) {
        return overWorkMinutesList.stream().mapToInt(OverWorkMinutes::getOverWorkMinutes).sum();
    }
}

package com.kintai.datasource.value;

import lombok.Getter;

import java.util.List;

public class OverWorkMinutes {
    @Getter
    private int overWorkMinutes;

    public OverWorkMinutes(int overWorkMinutes) {
        this.overWorkMinutes = overWorkMinutes;
    }

    /**
     * 残業時間を計算する。残業時間は分単位で出力
     * 作業開始から8時間経過以降を残業とする。
     * @return 残業時間
     */
    public OverWorkMinutes(WorkMinutes workMinutes) {
        this.overWorkMinutes = calculateOverWorkMinutes(workMinutes.getWorkMinutes());
    }

    public OverWorkMinutes(List<OverWorkMinutes> overWorkMinutesList) {
        this.overWorkMinutes = sumOverWorkMinutes(overWorkMinutesList);
    }

    protected int calculateOverWorkMinutes(int workMinutes) {
        return Math.max(workMinutes - 8 * 60, 0);
    }

    protected int sumOverWorkMinutes(List<OverWorkMinutes> overWorkMinutesList) {
        return overWorkMinutesList.stream().mapToInt(overWorkMinutes -> overWorkMinutes.getOverWorkMinutes()).sum();
    }
}

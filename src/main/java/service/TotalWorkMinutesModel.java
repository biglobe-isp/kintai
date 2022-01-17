package service;

import lombok.Getter;

@Getter
public final class TotalWorkMinutesModel {
    private final int totalWorkMinutes;
    private final int totalOverWorkMinutes;

    TotalWorkMinutesModel(int totalWorkMinutes, int totalOverWorkMinutes) {
        if (totalWorkMinutes < 0) {
            throw new IllegalArgumentException("総勤務時間は0分以上である必要があります。");
        }

        if (totalOverWorkMinutes < 0) {
            throw new IllegalArgumentException("総残業時間は0分以上である必要があります。");
        }

        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }
}

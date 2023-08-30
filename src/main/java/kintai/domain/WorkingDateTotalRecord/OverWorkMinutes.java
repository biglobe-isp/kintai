package kintai.domain.WorkingDateTotalRecord;

import lombok.Value;

@Value
public class OverWorkMinutes {
    int overWorkMinutes;

    public OverWorkMinutes(int overWorkMinutes){
        if(overWorkMinutes < 0){
            throw new IllegalArgumentException("残業時間は0以上");
        }
        this.overWorkMinutes = overWorkMinutes;
    }

    public static OverWorkMinutes calcOverWorkMinutes(int workMinutes) {

        int overWorkM = workMinutes - 8 * 60;
        if(overWorkM < 0){
            overWorkM =0;
        }
        return new OverWorkMinutes(overWorkM);
    }
}

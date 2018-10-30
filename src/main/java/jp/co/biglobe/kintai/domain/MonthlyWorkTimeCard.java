package jp.co.biglobe.kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class MonthlyWorkTimeCard {

    @Getter
    private int workHours;
    @Getter
    private int workMinutes;

    @Getter
    private int overWorkHours;
    @Getter
    private int overWorkMinutes;


    public MonthlyWorkTimeCard(int totalWorkMinutes, int totalOverWorkMinutes){
        this.workHours = totalWorkMinutes / 60;
        this.workMinutes = totalWorkMinutes % 60;
        this.overWorkHours = totalOverWorkMinutes / 60;
        this.overWorkMinutes = totalOverWorkMinutes % 60;
    }
}

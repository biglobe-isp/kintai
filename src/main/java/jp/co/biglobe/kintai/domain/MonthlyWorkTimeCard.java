package jp.co.biglobe.kintai.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MonthlyWorkTimeCard {

    private int workHours;
    private int workMinutes;

    private int overWorkHours;
    private int overWorkMinutes;


    public MonthlyWorkTimeCard(int totalWorkMinutes, int totalOverWorkMinutes){
        this.workHours = totalWorkMinutes / 60;
        this.workMinutes = totalWorkMinutes % 60;
        this.overWorkHours = totalOverWorkMinutes / 60;
        this.overWorkMinutes = totalOverWorkMinutes % 60;
    }

    public int getWorkHours() {
        return workHours;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public int getOverWorkHours() {
        return overWorkHours;
    }

    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }

}

package com.naosim.dddwork.domain;

//DTO
public class KintaiTotalModel {
    private int totalWorkMinutes, totalOverWorkMinutes;

    public KintaiTotalModel(int totalWorkMinutes, int totalOverWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }

    public int getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public int getTotalOverWorkMinutes() {
        return totalOverWorkMinutes;
    }
}

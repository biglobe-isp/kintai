package com.naosim.dddwork.domain;

import lombok.Getter;

@Getter
public class ResultTotal {
    private int totalWorkMinutes;
    private int totalOverWorkMinutes;

    public ResultTotal(int totalWorkMinutes, int totalOverWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }
}

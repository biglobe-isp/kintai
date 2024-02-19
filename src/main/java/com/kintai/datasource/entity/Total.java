package com.kintai.datasource.entity;

import com.kintai.datasource.value.OverWorkMinutes;
import com.kintai.datasource.value.TotalMonth;
import com.kintai.datasource.value.WorkMinutes;
import lombok.Getter;

public class Total {
    @Getter
    private final TotalMonth totalMonth;

    @Getter
    private final WorkMinutes workMinutes;

    @Getter
    private final OverWorkMinutes overWorkMinutes;

    public Total(TotalMonth totalMonth, WorkMinutes workMinutes, OverWorkMinutes overWorkMinutes) {
        this.totalMonth = totalMonth;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
    }
}

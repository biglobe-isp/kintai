package com.naosim.dddwork.kintai.domain.timerecord;

public enum TimeIntervalComparedStatus {
    WITHIN,
    CONTAIN_ALL,
    EQUAL_OR_BEFORE,
    EQUAL_OR_AFTER,
    OUT_OF;

    private TimeIntervalComparedStatus() {}
}

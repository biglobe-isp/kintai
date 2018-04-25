package com.naosim.dddwork.kintai_management.service.total;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 勤怠管理集計ステータス.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum KintaiManagementTotalResultStatus {
    /**
     * 集計できた時
     */
    OK,;

    public boolean isOK() {
        return this == OK;
    }
}

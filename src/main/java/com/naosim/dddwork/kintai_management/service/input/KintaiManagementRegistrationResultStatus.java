package com.naosim.dddwork.kintai_management.service.input;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 勤怠管理登録ステータス.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum KintaiManagementRegistrationResultStatus {
    /**
     * 情報登録できた時
     */
    OK,
    /**
     * 既に登録されていると同じを登録しようとした時
     */
    ALREADY_REGISTERED,;

    public boolean isOK() {
        return this == OK;
    }
}

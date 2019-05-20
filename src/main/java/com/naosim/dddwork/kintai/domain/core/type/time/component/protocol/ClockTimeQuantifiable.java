package com.naosim.dddwork.kintai.domain.core.type.time.component.protocol;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;


/**
 * 時刻量化可能を規定する
 */
public interface ClockTimeQuantifiable {

    //TODO: 基準が曖昧かな？
    AmountOfMinutes quantityOfMinutes();
}

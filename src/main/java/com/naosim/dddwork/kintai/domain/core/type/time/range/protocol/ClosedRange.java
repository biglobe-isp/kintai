package com.naosim.dddwork.kintai.domain.core.type.time.range.protocol;


/**
 * 閉区間時刻範囲を規定する
 *
 * @param <Start> 開始時刻（含む）
 * @param <Last> 終了時刻（含む）
 */
public interface ClosedRange<Start, Last> {

    Start firstTime();
    Last lastTime();
}

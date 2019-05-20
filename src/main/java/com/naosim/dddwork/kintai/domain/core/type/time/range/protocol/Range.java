package com.naosim.dddwork.kintai.domain.core.type.time.range.protocol;


import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;

/**
 * 半開区間時刻範囲を規定する
 *
 * @param <Begin> 開始時刻（含む）
 * @param <End> 終了時刻（含まない）
 */
public interface Range<Begin, End> {

    /** 範囲の開始時刻を返す． */
    Begin beginTime();
    /** 範囲の終了時刻を返す． */
    End endTime();
    /** 範囲の時間量（分）を返す．． */
    AmountOfMinutes amountOfMinutes();

//    Range overlappedTimeRangeWith(Range other);
//    boolean isArrangedInOrder();
//    boolean isArrangedInReverseOrder();


}

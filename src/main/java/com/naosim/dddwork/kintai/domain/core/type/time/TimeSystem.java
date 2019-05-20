package com.naosim.dddwork.kintai.domain.core.type.time;

import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.EndTime;
import com.naosim.dddwork.kintai.domain.core.type.time.component.HourOfDay;
import com.naosim.dddwork.kintai.domain.core.type.time.component.MinuteOfHour;


/**
 * 時間システム
 * <pre>
 *     勤怠システムは業務終了時刻が日をまたぐことがある。
 *     日の区切り（日境界）を決めたものを時間システムと呼ぶことにする。
 *     例えば 9:00 を日境界とした場合の時間システムを {@link #TIME33} としている。
 *
 *     TODO: フレックスとかだとどうなるのだろうか...
 * </pre>
 */
public enum TimeSystem {

    TIME30(HourOfDay.of(6), HourOfDay.of(30)),
    TIME33(HourOfDay.of(9), HourOfDay.of(33)),
    ;

    private final HourOfDay _dayBoundaryHour;
    private final HourOfDay _maxHour;

    TimeSystem(HourOfDay dayBoundaryTime, HourOfDay maxHour) {
        _dayBoundaryHour = dayBoundaryTime;
        _maxHour = maxHour;
    }


    /* 値 */

    /**
     * このタイムシステムにおける日境界の時刻（時）を返す．
     * @return 日境界の時刻（時）
     */
    public HourOfDay dayBoundaryHour() {
        return _dayBoundaryHour;
    }

    /**
     * このタイムシステムにおける最大の {@link HourOfDay} を返す．
     * @return 最大の {@link HourOfDay}
     */
    public HourOfDay maxHour() {
        return _maxHour;
    }


    /* 評価 */

    /**
     * 指定された {@link HourOfDay} が日境界かどうかを判定して返す．
     *
     * @param hourOfDay 時
     * @return 日境界の場合は true そうでない場合は false
     */
    public boolean isDayBoundaryConsistentWith(HourOfDay hourOfDay) {
        return hourOfDay.equals(_dayBoundaryHour);
    }



    /* 導出 */

    /**
     * このタイムシステムが許容する最小の開始時刻を返す．
     *
     * @return 許容する最小の開始時刻
     */
    public BeginTime minBeginTime() {
        return BeginTime.of(_dayBoundaryHour, MinuteOfHour.min());
    }

    /**
     * このタイムシステムが許容する最大の終了時刻を返す．
     *
     * @return 許容する最大の終了時刻
     */
    public EndTime maxEndTime() {
        return EndTime.of(_maxHour, MinuteOfHour.min());
    }

}

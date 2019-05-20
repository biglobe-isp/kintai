package com.naosim.dddwork.kintai.domain.core.type.time.range;


import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.EndTime;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import com.naosim.dddwork.kintai.domain.core.type.time.range.protocol.ClosedRange;
import lombok.ToString;

/**
 * 閉区間の時間範囲
 * <pre>
 *     TimeRange: ●---○ （end値は含まない）
 *     ClosedTimeRange: ●---● （end値を含む） ← こちら！
 *
 *     参考
 *     https://www.wikiwand.com/ja/%E5%8C%BA%E9%96%93_(%E6%95%B0%E5%AD%A6)#/.E7.94.A8.E8.AA.9E
 * </pre>
 */
@ToString
//TODO: FirstTime と BeginTime を設ける
public class ClosedTimeRange implements ClosedRange<BeginTime, EndTime> {

    final BeginTime firstTime;
    final EndTime lastTime;


    /* 生成 */

    private ClosedTimeRange(BeginTime firstTime, EndTime lastTime) {

        this.firstTime = firstTime;
        this.lastTime = lastTime;
    }

    public static ClosedTimeRange of(BeginTime beginTime, EndTime endTime) {
        return new ClosedTimeRange(beginTime, endTime);
    }



    @Override
    public BeginTime firstTime() {
        return firstTime;
    }

    public EndTime lastTime() {
        return lastTime;
    }


    /* 重なり */

    /**
     * 指定された時刻がこの閉区間時間範囲に含まれるかどうかを判定して返す．
     * <pre>
     *     例）この閉区間時間範囲が 10:00 ~ 13:00 の場合、
     *           9:59 → 含まれない
     *          10:00 → 含まれる
     *          13:00 → 含まれる
     *          13:01 → 含まれない
     * </pre>
     *
     * @param time 判定したい時刻
     *
     * @return この範囲に含まれる場合は true そうでない場合は false
     */
    public boolean contains(TimeOrderComparable time) {

        if (time.isBefore(firstTime) || time.isAfter(lastTime)) {
            return false;
        }

        return true;
    }
}

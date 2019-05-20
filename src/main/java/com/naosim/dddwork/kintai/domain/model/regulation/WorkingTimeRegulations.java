package com.naosim.dddwork.kintai.domain.model.regulation;

import com.naosim.dddwork.kintai.domain.core.type.time.TimeSystem;
import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.EndTime;
import com.naosim.dddwork.kintai.domain.core.type.time.range.ClosedTimeRange;
import com.naosim.dddwork.kintai.domain.core.type.time.range.TimeRange;


/**
 * 勤務時間規定
 */
public final class WorkingTimeRegulations {

    /** 適用する時間システム */
    public static final TimeSystem APPLIED_TIME_SYSTEM = TimeSystem.TIME33;

    //THINK: 早朝出勤可能とかになったらどうする？
    /** 通常勤務時間帯 */
    public static final TimeRange REGULAR_WORKING_HOURS = TimeRange.of(BeginTime.of(9, 0), EndTime.of(18, 0));
    /** サービス勤務時間帯 */
    public static final TimeRange UNPAID_OVERTIME_HOURS = TimeRange.of(BeginTime.of(24, 0), APPLIED_TIME_SYSTEM.maxEndTime());


    /**
     * 法定労働時間量（分）
     * <pre>
     *     これを超えると残業時間となる。
     *     ただし休憩時間帯やサービス勤務時間帯は除く。
     * </pre>
     */
    public static final AmountOfMinutes LEGAL_WORKING_MINUTES = WorkTimeCalculation.actualTotalWorkMinutesIn(REGULAR_WORKING_HOURS);

    /** 指定可能な作業開始時刻の範囲 */
    public static final ClosedTimeRange SPECIFIABLE_BEGIN_TIME_RANGE = ClosedTimeRange.of(BeginTime.of(9, 0), EndTime.of(9, 0));

    /** 指定可能な作業終了時刻の範囲 */
    public static final ClosedTimeRange SPECIFIABLE_END_TIME_RANGE = ClosedTimeRange.of(BeginTime.of(9, 1), APPLIED_TIME_SYSTEM.maxEndTime());
}

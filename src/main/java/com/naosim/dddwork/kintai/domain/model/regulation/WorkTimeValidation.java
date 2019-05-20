package com.naosim.dddwork.kintai.domain.model.regulation;

import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;

import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.SPECIFIABLE_BEGIN_TIME_RANGE;


/**
 * 作業時間検査
 */
public final class WorkTimeValidation {


    /* 検査 */

    public static void validate(BeginTime beginTime) {

        if (SPECIFIABLE_BEGIN_TIME_RANGE.contains(beginTime)) {
            return;
        }

        final String message = String.format("開始時刻指定可能範囲外です。[指定値=%s, 指定可能範囲=%s]", beginTime, SPECIFIABLE_BEGIN_TIME_RANGE);
//TODO: Validation例外どうするか...
//        throw new BusinessException(message);
        throw new IllegalArgumentException(message);
    }
}

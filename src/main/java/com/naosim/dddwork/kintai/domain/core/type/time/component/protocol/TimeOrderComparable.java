package com.naosim.dddwork.kintai.domain.core.type.time.component.protocol;

import java.util.Arrays;


/**
 * 時刻順序比較可能を規定する
 *
 * @param <T> 時刻を表現する型
 */
public interface TimeOrderComparable<T> extends ClockTimeQuantifiable {

    boolean isBefore(ClockTimeQuantifiable other);
    boolean isAfter(ClockTimeQuantifiable other);

    T min();
    T max();


    /* ユーティリティ */

    /**
     * 指定された評価対象時刻のなかから、最も早い時刻を探して返す．
     *
     * @param evaluationTargets 評価対象時刻
     * @param <T> 時刻順序比較可能な時刻型
     *
     * @return 最も早い時刻
     */
    static <T extends TimeOrderComparable<T>> T pickEarlier(TimeOrderComparable<T>... evaluationTargets) {

        if (evaluationTargets.length == 0) {
            throw new IllegalArgumentException("引数が指定されていません。");
        }

        TimeOrderComparable<T> later = Arrays.stream(evaluationTargets).reduce(

                evaluationTargets[0].max(),
                (minCandidate, target) -> (target.isBefore(minCandidate)) ? target : minCandidate
        );
        return (T) later;
    }

    /**
     * 指定された評価対象時刻のなかから、最も遅い時刻を探して返す．
     *
     * @param evaluationTargets 評価対象時刻
     * @param <T> 時刻順序比較可能な時刻型
     *
     * @return 最も遅い時刻
     */
    static <T extends TimeOrderComparable<T>> T pickLater(TimeOrderComparable<T>... evaluationTargets) {

        if (evaluationTargets.length == 0) {
            throw new IllegalArgumentException("引数が指定されていません。");
        }

        TimeOrderComparable<T> later = Arrays.stream(evaluationTargets).reduce(

                evaluationTargets[0].min(),
                (maxCandidate, target) -> target.isAfter(maxCandidate) ? target : maxCandidate
        );
        return (T) later;
    }
}

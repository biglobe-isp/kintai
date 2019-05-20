package com.naosim.dddwork.kintai.domain.core.type.time.amount;

import com.naosim.dddwork.kintai.shared.easy.Validatable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.util.Arrays;


/**
 * 分の量
 */
@EqualsAndHashCode
@ToString
public class AmountOfMinutes implements Validatable {

    static final int MIN = 0;
    static final int MAX = 60 * 33;


    @Range(min=MIN, max=MAX)
    final int value;


    /* 生成 */

    private AmountOfMinutes(int value) {

        this.value = value;
        validate(this);
    }

    private AmountOfMinutes(long value) {

        validate(value);
        this.value = (int) value;
    }


    public static AmountOfMinutes of(int value) {
        return new AmountOfMinutes(value);
    }

    public static AmountOfMinutes of(long value) {
        return new AmountOfMinutes(value);
    }


    public static AmountOfMinutes max() {
        return new AmountOfMinutes(MAX);
    }

    public static AmountOfMinutes zero() {
        return AmountOfMinutes.of(0);
    }



    /* 値 */

    public int rawValue() {
        return value;
    }


    /* 演算 */

    public AmountOfMinutes plus(AmountOfMinutes other) {
        return new AmountOfMinutes(value + other.value);
    }

    public AmountOfMinutes minus(AmountOfMinutes other) {

        int operatedValue =  (value < other.value) ? 0 : value - other.value;
        return new AmountOfMinutes(operatedValue);
    }


    /* 比較 */

    public boolean isLessThan(AmountOfMinutes other) {
        return value < other.value;
    }

    public boolean isGreaterThan(AmountOfMinutes other) {
        return value > other.value;
    }



    /* ユーティリティ */

    public static AmountOfMinutes findMinimum(AmountOfMinutes... evaluationTargets) {

        return Arrays.stream(evaluationTargets).reduce(
                AmountOfMinutes.max(),
                (minCandidate, target) -> (target.isLessThan(minCandidate)) ? target : minCandidate
        );
    }
}

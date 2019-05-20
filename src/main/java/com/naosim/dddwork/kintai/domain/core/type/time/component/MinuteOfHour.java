package com.naosim.dddwork.kintai.domain.core.type.time.component;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.ClockTimeQuantifiable;
import com.naosim.dddwork.kintai.shared.easy.Validatable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;


/**
 * 分（時刻構成要素）
 */
@EqualsAndHashCode
@ToString
public class MinuteOfHour implements Validatable, ClockTimeQuantifiable {

    static final int MIN = 0;
    static final int MAX = 59;

    @Range(min=MIN, max=MAX)
    final int value;


    /* 生成 */

    private MinuteOfHour(int value) {

        this.value = value;
        validate(this);
    }

    public static MinuteOfHour min() {
        return new MinuteOfHour(MIN);
    }

    public static MinuteOfHour max() {
        return new MinuteOfHour(MAX);
    }

    public static MinuteOfHour of(int value) {
        return new MinuteOfHour(value);
    }

    public MinuteOfHour copy() {
        return new MinuteOfHour(value);
    }


    /* 時間量 */

    private AmountOfMinutes _amountOfMinutes() {
        return AmountOfMinutes.of(value);
    }

    @Override
    public AmountOfMinutes quantityOfMinutes() {
        return _amountOfMinutes();
    }
}

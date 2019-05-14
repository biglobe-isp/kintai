package com.naosim.dddwork.kintai.domain.core.type.time.component;

import com.naosim.dddwork.kintai.shared.easy.Validatable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;


/**
 * 時（時刻構成要素）
 */
@EqualsAndHashCode
@ToString
@Getter
//TODO: Getterは暫定的に使用中
public class HourOfDay implements Validatable {

    static final int MIN = 0;
    static final int MAX = 24 + 9;

    @Range(min=MIN, max=MAX)
    final int value;

    public static HourOfDay of(int value) {
        return new HourOfDay(value);
    }

    public HourOfDay(int value) {

        this.value = value;
        validate(this);
    }
}

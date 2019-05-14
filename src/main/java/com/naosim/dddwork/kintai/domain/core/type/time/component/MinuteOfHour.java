package com.naosim.dddwork.kintai.domain.core.type.time.component;

import com.naosim.dddwork.kintai.shared.easy.Validatable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;


/**
 * 分（時刻構成要素）
 */
@EqualsAndHashCode
@ToString
@Getter
//TODO: Getterは暫定的に使用中
public class MinuteOfHour implements Validatable {

    static final int MIN = 0;
    static final int MAX = 59;

    @Range(min=MIN, max=MAX)
    final int value;

    public static MinuteOfHour of(int value) {
        return new MinuteOfHour(value);
    }

    public MinuteOfHour(int value) {

        this.value = value;
        validate(this);
    }
}

package com.naosim.dddwork.kintai.domain.core.type.time.amount;

import com.naosim.dddwork.kintai.shared.easy.Validatable;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;


/**
 * 分の量
 */
@ToString
@Getter //TODO: 消せるか確認
public class AmountOfMinutes implements Validatable {

    static final int MIN = 0;
    static final int MAX = 60 * 24;


    @Range(min=MIN, max=MAX)
    final int value;


    public static AmountOfMinutes of(int value) {
        return new AmountOfMinutes(value);
    }

    public AmountOfMinutes(int value) {

        this.value = value;
        validate(this);
    }

}

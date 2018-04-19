package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.MethodType;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MethodTypeForm {

    @Getter
    @EnumForApiValue(MethodType.class)
    private String value;

    public MethodType getValueObject() {
        return EnumApiValueConverter.convert(MethodType.class, value);
    }
}

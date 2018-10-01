package com.naosim.dddwork.api.form;

import com.naosim.dddwork.api.attendance.MethodType;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class MethodTypeForm implements FormToValueObject<MethodType> {

    @Getter
    @NotNull
    private String value;

    public MethodTypeForm(String[] args) {

        if (args == null || args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        this.value = args[0];
    }

    @Override
    public MethodType getValueObject() {
        return new MethodType(this.getValue());
    }
}

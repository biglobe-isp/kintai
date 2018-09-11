//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.MethodType;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.validation.EnumForApiValue;

import java.beans.ConstructorProperties;

public class MethodTypeForm {
    @EnumForApiValue(MethodType.class)
    private String value;

    public MethodType getValueObject() {
        return (MethodType) EnumApiValueConverter.convert(MethodType.class, this.value);
    }

    public String toString() {
        return "MethodTypeForm(" + this.getValue() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MethodTypeForm)) {
            return false;
        } else {
            MethodTypeForm other = (MethodTypeForm) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$value = this.getValue();
                Object other$value = other.getValue();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MethodTypeForm;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $value = this.getValue();
        //int result = result * 59 + ($value == null ? 43 : $value.hashCode());
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    @ConstructorProperties({"value"})
    public MethodTypeForm(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

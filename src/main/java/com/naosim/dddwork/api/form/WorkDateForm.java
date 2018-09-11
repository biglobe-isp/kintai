//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.WorkDate;
import jp.co.biglobe.lib.publication.validation.FormatDate;

import javax.validation.ValidationException;

public class WorkDateForm {
    @FormatDate
    private String value;

    public WorkDateForm(String value) {
        if (value != null) {
            String[] tmpStr = value.split(":", -1);
            if (tmpStr.length > 1) {
                value = tmpStr[1];
            }

            if (!"-date".equals(tmpStr[0])) {
                throw new ValidationException("指定された形式で入力してください：出勤日");
            }
        }

        this.value = value;
    }

    public WorkDate getValueObject() {
        return new WorkDate(this.value);
    }

    public String toString() {
        return "WorkDateForm(" + this.getValue() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WorkDateForm)) {
            return false;
        } else {
            WorkDateForm other = (WorkDateForm) o;
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
        return other instanceof WorkDateForm;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $value = this.getValue();
        //int result = result * 59 + ($value == null ? 43 : $value.hashCode());
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String getValue() {
        return this.value;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.WorkTime;

import javax.validation.ValidationException;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class WorkTimeFromForm {
    @Size(
            min = 4,
            max = 4
    )
    @Pattern(
            regexp = "^[0-9]*$"
    )
    private String value;

    public WorkTimeFromForm(String value) {
        if (value != null) {
            String[] tmpStr = value.split(":", -1);
            if (tmpStr.length > 1) {
                value = tmpStr[1];
            }

            if (!"-start".equals(tmpStr[0])) {
                throw new ValidationException("指定された形式で入力してください：出勤時刻");
            }
        }

        this.value = value;
    }

    public WorkTime getValueObject() {
        return new WorkTime(this.value);
    }

    public String toString() {
        return "WorkTimeFromForm(" + this.getValue() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WorkTimeFromForm)) {
            return false;
        } else {
            WorkTimeFromForm other = (WorkTimeFromForm) o;
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
        return other instanceof WorkTimeFromForm;
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

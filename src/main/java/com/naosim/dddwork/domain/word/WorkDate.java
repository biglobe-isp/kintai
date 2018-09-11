//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.word;

import jp.co.biglobe.lib.publication.date.DateParser;

import java.time.LocalDate;

public class WorkDate {
    private final LocalDate value;

    public WorkDate(String str) {
        if (str != null) {
            this.value = DateParser.parse_yyyyMMdd(str);
        } else {
            this.value = null;
        }

    }

    public LocalDate convertLocalDate() {
        return this.value;
    }

    public String toString() {
        return "WorkDate(" + this.getValue() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WorkDate)) {
            return false;
        } else {
            WorkDate other = (WorkDate) o;
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
        return other instanceof WorkDate;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $value = this.getValue();
        //int result = result * 59 + ($value == null ? 43 : $value.hashCode());
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public LocalDate getValue() {
        return this.value;
    }
}

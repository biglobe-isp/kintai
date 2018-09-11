//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.word;

import jp.co.biglobe.lib.publication.date.DateParser;

import java.math.BigDecimal;
import java.time.LocalTime;

public class WorkTime {
    private final LocalTime value;

    public WorkTime(String str) {
        if (str != null) {
            this.value = DateParser.parse_HHmmss(str + "00");
        } else {
            this.value = null;
        }

    }

    public WorkingHour getHour() {
        return new WorkingHour(BigDecimal.valueOf((long) this.value.getHour()));
    }

    public WorkingMinute getMinute() {
        return new WorkingMinute(BigDecimal.valueOf((long) this.value.getMinute()));
    }

    public LocalTime convertLocalTime() {
        return this.value;
    }

    public String toString() {
        return "WorkTime(" + this.getValue() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WorkTime)) {
            return false;
        } else {
            WorkTime other = (WorkTime) o;
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
        return other instanceof WorkTime;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $value = this.getValue();
        //int result = result * 59 + ($value == null ? 43 : $value.hashCode());
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public LocalTime getValue() {
        return this.value;
    }
}

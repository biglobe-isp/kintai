//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.duty;

import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkTime;
import com.naosim.dddwork.domain.word.WorkingMinute;

import java.math.BigDecimal;

public class WorkingData {
    private WorkDate workDate;
    private WorkTime workTimeFrom;
    private WorkTime workTimeTo;
    private WorkingMinute workingMinute;
    private WorkingMinute overWorkingMinute;

    public WorkingData(String str) {
        if (str != null && !"".equals(str)) {
            String[] strings = str.replaceAll("\"", "").replaceAll("-", "").replaceAll(":", "").split(",", -1);
            if (strings.length == 5) {
                this.workDate = new WorkDate(strings[0]);
                this.workTimeFrom = new WorkTime(strings[1]);
                this.workTimeTo = new WorkTime(strings[2]);
                this.workingMinute = new WorkingMinute(BigDecimal.valueOf(Double.valueOf(strings[3])).setScale(2, 4));
                this.overWorkingMinute = new WorkingMinute(BigDecimal.valueOf(Double.valueOf(strings[4])).setScale(2, 4));
            } else {
                throw new IllegalArgumentException("取り込み元のCSVファイルが不正です");
            }
        } else {
            throw new IllegalArgumentException("取り込み元のCSVファイルが不正です");
        }
    }

    WorkingData(WorkDate workDate, WorkTime workTimeFrom, WorkTime workTimeTo, WorkingMinute convertedWorkMinute, WorkingMinute convertedOverWorkMinute) {
        this.workDate = workDate;
        this.workTimeFrom = workTimeFrom;
        this.workTimeTo = workTimeTo;
        this.workingMinute = convertedWorkMinute;
        this.overWorkingMinute = convertedOverWorkMinute;
    }

    public String toString() {
        return "WorkingData(" + this.getWorkDate() + ", " + this.getWorkTimeFrom() + ", " + this.getWorkTimeTo() + ", " + this.getWorkingMinute() + ", " + this.getOverWorkingMinute() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WorkingData)) {
            return false;
        } else {
            WorkingData other = (WorkingData) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71:
                {
                    Object this$workDate = this.getWorkDate();
                    Object other$workDate = other.getWorkDate();
                    if (this$workDate == null) {
                        if (other$workDate == null) {
                            break label71;
                        }
                    } else if (this$workDate.equals(other$workDate)) {
                        break label71;
                    }

                    return false;
                }

                Object this$workTimeFrom = this.getWorkTimeFrom();
                Object other$workTimeFrom = other.getWorkTimeFrom();
                if (this$workTimeFrom == null) {
                    if (other$workTimeFrom != null) {
                        return false;
                    }
                } else if (!this$workTimeFrom.equals(other$workTimeFrom)) {
                    return false;
                }

                label57:
                {
                    Object this$workTimeTo = this.getWorkTimeTo();
                    Object other$workTimeTo = other.getWorkTimeTo();
                    if (this$workTimeTo == null) {
                        if (other$workTimeTo == null) {
                            break label57;
                        }
                    } else if (this$workTimeTo.equals(other$workTimeTo)) {
                        break label57;
                    }

                    return false;
                }

                Object this$workingMinute = this.getWorkingMinute();
                Object other$workingMinute = other.getWorkingMinute();
                if (this$workingMinute == null) {
                    if (other$workingMinute != null) {
                        return false;
                    }
                } else if (!this$workingMinute.equals(other$workingMinute)) {
                    return false;
                }

                Object this$overWorkingMinute = this.getOverWorkingMinute();
                Object other$overWorkingMinute = other.getOverWorkingMinute();
                if (this$overWorkingMinute == null) {
                    if (other$overWorkingMinute == null) {
                        return true;
                    }
                } else if (this$overWorkingMinute.equals(other$overWorkingMinute)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkingData;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $workDate = this.getWorkDate();
        //int result = result * 59 + ($workDate == null ? 43 : $workDate.hashCode());
        result = result * 59 + ($workDate == null ? 43 : $workDate.hashCode());
        Object $workTimeFrom = this.getWorkTimeFrom();
        result = result * 59 + ($workTimeFrom == null ? 43 : $workTimeFrom.hashCode());
        Object $workTimeTo = this.getWorkTimeTo();
        result = result * 59 + ($workTimeTo == null ? 43 : $workTimeTo.hashCode());
        Object $workingMinute = this.getWorkingMinute();
        result = result * 59 + ($workingMinute == null ? 43 : $workingMinute.hashCode());
        Object $overWorkingMinute = this.getOverWorkingMinute();
        result = result * 59 + ($overWorkingMinute == null ? 43 : $overWorkingMinute.hashCode());
        return result;
    }

    public WorkDate getWorkDate() {
        return this.workDate;
    }

    public WorkTime getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    public WorkTime getWorkTimeTo() {
        return this.workTimeTo;
    }

    public WorkingMinute getWorkingMinute() {
        return this.workingMinute;
    }

    public WorkingMinute getOverWorkingMinute() {
        return this.overWorkingMinute;
    }
}

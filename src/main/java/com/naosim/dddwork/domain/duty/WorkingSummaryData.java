//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.duty;

import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkingMinute;

public class WorkingSummaryData {
    private WorkDate workDateStart;
    private WorkDate workDateEnd;
    private WorkingMinute workingMinuteSummary;
    private WorkingMinute overWorkingMinuteSummary;

    public WorkingSummaryData(WorkDate workDateStart, WorkDate workDateEnd, WorkingMinute workingMinuteSummary, WorkingMinute overWorkingMinuteSummary) {
        this.workDateStart = workDateStart;
        this.workDateEnd = workDateEnd;
        this.workingMinuteSummary = workingMinuteSummary;
        this.overWorkingMinuteSummary = overWorkingMinuteSummary;
    }

    public String toString() {
        return "WorkingSummaryData(" + this.getWorkDateStart() + ", " + this.getWorkDateEnd() + ", " + this.getWorkingMinuteSummary() + ", " + this.getOverWorkingMinuteSummary() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WorkingSummaryData)) {
            return false;
        } else {
            WorkingSummaryData other = (WorkingSummaryData) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59:
                {
                    Object this$workDateStart = this.getWorkDateStart();
                    Object other$workDateStart = other.getWorkDateStart();
                    if (this$workDateStart == null) {
                        if (other$workDateStart == null) {
                            break label59;
                        }
                    } else if (this$workDateStart.equals(other$workDateStart)) {
                        break label59;
                    }

                    return false;
                }

                Object this$workDateEnd = this.getWorkDateEnd();
                Object other$workDateEnd = other.getWorkDateEnd();
                if (this$workDateEnd == null) {
                    if (other$workDateEnd != null) {
                        return false;
                    }
                } else if (!this$workDateEnd.equals(other$workDateEnd)) {
                    return false;
                }

                Object this$workingMinuteSummary = this.getWorkingMinuteSummary();
                Object other$workingMinuteSummary = other.getWorkingMinuteSummary();
                if (this$workingMinuteSummary == null) {
                    if (other$workingMinuteSummary != null) {
                        return false;
                    }
                } else if (!this$workingMinuteSummary.equals(other$workingMinuteSummary)) {
                    return false;
                }

                Object this$overWorkingMinuteSummary = this.getOverWorkingMinuteSummary();
                Object other$overWorkingMinuteSummary = other.getOverWorkingMinuteSummary();
                if (this$overWorkingMinuteSummary == null) {
                    if (other$overWorkingMinuteSummary != null) {
                        return false;
                    }
                } else if (!this$overWorkingMinuteSummary.equals(other$overWorkingMinuteSummary)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkingSummaryData;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $workDateStart = this.getWorkDateStart();
        //int result = result * 59 + ($workDateStart == null ? 43 : $workDateStart.hashCode());
        result = result * 59 + ($workDateStart == null ? 43 : $workDateStart.hashCode());
        Object $workDateEnd = this.getWorkDateEnd();
        result = result * 59 + ($workDateEnd == null ? 43 : $workDateEnd.hashCode());
        Object $workingMinuteSummary = this.getWorkingMinuteSummary();
        result = result * 59 + ($workingMinuteSummary == null ? 43 : $workingMinuteSummary.hashCode());
        Object $overWorkingMinuteSummary = this.getOverWorkingMinuteSummary();
        result = result * 59 + ($overWorkingMinuteSummary == null ? 43 : $overWorkingMinuteSummary.hashCode());
        return result;
    }

    public WorkDate getWorkDateStart() {
        return this.workDateStart;
    }

    public WorkDate getWorkDateEnd() {
        return this.workDateEnd;
    }

    public WorkingMinute getWorkingMinuteSummary() {
        return this.workingMinuteSummary;
    }

    public WorkingMinute getOverWorkingMinuteSummary() {
        return this.overWorkingMinuteSummary;
    }

    public void setWorkDateStart(WorkDate workDateStart) {
        this.workDateStart = workDateStart;
    }

    public void setWorkDateEnd(WorkDate workDateEnd) {
        this.workDateEnd = workDateEnd;
    }

    public void setWorkingMinuteSummary(WorkingMinute workingMinuteSummary) {
        this.workingMinuteSummary = workingMinuteSummary;
    }

    public void setOverWorkingMinuteSummary(WorkingMinute overWorkingMinuteSummary) {
        this.overWorkingMinuteSummary = overWorkingMinuteSummary;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.service.input;

import com.naosim.dddwork.domain.word.MethodType;
import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkTime;

public class KintaiKanriInputServiceInput {
    private MethodType methodType;
    private WorkDate workDate;
    private WorkTime workTimeFrom;
    private WorkTime workTimeTo;

    public KintaiKanriInputServiceInput(MethodType methodType, WorkDate workDate, WorkTime workTimeFrom, WorkTime workTimeTo) {
        this.methodType = methodType;
        this.workDate = workDate;
        this.workTimeFrom = workTimeFrom;
        this.workTimeTo = workTimeTo;
    }

    public MethodType getMethodType() {
        return this.methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public WorkDate getWorkDate() {
        return this.workDate;
    }

    public void setWorkDate(WorkDate workDate) {
        this.workDate = workDate;
    }

    public WorkTime getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    public void setWorkTimeFrom(WorkTime workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
    }

    public WorkTime getWorkTimeTo() {
        return this.workTimeTo;
    }

    public void setWorkTimeTo(WorkTime workTimeTo) {
        this.workTimeTo = workTimeTo;
    }
}

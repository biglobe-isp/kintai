package com.kintai.datasource.value.expansion.workdate;

import com.kintai.datasource.value.WorkDate;
import com.kintai.exception.ValidatorException;

public class WorkDateExtend extends WorkDate {
    /* 不要文字 */
    private final String parseString = "-date:";

    /**
     * コンストラクタ
     *
     * @param workDate 勤務日
     */
    public WorkDateExtend(String workDate) throws ValidatorException {
        super(workDate);
        this.workDate = parseWorkDate(workDate);
    }

    protected String parseWorkDate(String workDate) {
        return workDate.replace(parseString, "");
    }

    @Override
    protected void isDate(String workDate) throws ValidatorException {
        String parsedWorkDate = parseWorkDate(workDate);
        super.isDate(parsedWorkDate);
    }

}

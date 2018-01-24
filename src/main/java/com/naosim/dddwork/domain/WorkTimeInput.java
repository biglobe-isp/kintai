package com.naosim.dddwork.domain;


import com.naosim.dddwork.api.form.WorkTimeInputForm;
import lombok.Getter;

public class WorkTimeInput {

    private WorkTimeInputParam workTimeInputParam;

    @Getter
    private int workMinutes;

    @Getter
    private int overWorkMinutes;

    /**
     * コンストラクタ
     *
     * @param WorkTimeInput
     */
    public WorkTimeInput(WorkTimeInputParam workTimeInputParam) {
        this.workTimeInputParam = workTimeInputParam;
        setWorkMinutes();
        setOverWorkMinutes(this.workMinutes);
    }

    /**
     * 勤務時間を取得する。
     *
     * @return
     */
    private void setWorkMinutes() {

        int startH = Integer.valueOf(workTimeInputParam.getStart().substring(0, 2));
        int startM = Integer.valueOf(workTimeInputParam.getStart().substring(2, 4));

        int endH = Integer.valueOf(workTimeInputParam.getEnd().substring(0, 2));
        int endM = Integer.valueOf(workTimeInputParam.getEnd().substring(2, 4));

        workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }
    }

    /**
     * 残業時間を取得する。
     *
     * @return
     */
    private void setOverWorkMinutes(int workMinutes) {
        overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
    }
}

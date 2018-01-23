package com.naosim.dddwork.domain;


public class WorkTimeInput {

    private WorkTimeInputForm workTimeInputForm;

    private int workMinutes;
    private int overWorkMinutes;

    /**
     * コンストラクタ
     *
     * @param workTimeInputForm
     */
    public WorkTimeInput(WorkTimeInputForm workTimeInputForm) {
        this.workTimeInputForm = workTimeInputForm;
    }

    /**
     * 勤務時間を取得する。
     *
     * @return
     */
    public int getWorkMinutes() {

        int startH = Integer.valueOf(workTimeInputForm.getStart().substring(0, 2));
        int startM = Integer.valueOf(workTimeInputForm.getStart().substring(2, 4));

        int endH = Integer.valueOf(workTimeInputForm.getEnd().substring(0, 2));
        int endM = Integer.valueOf(workTimeInputForm.getEnd().substring(2, 4));

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

        return workMinutes;
    }

    /**
     * 残業時間を取得する。
     *
     * @return
     */
    public int getOverWorkMinutes() {
        overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
        return overWorkMinutes;
    }
}

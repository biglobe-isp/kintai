package com.naosim.dddwork.domain.workdateandtime;


import com.naosim.dddwork.domain.workdateandtime.NormalWorkTimeMinutes;
import com.naosim.dddwork.domain.workdateandtime.OverWorkTimeMinutes;
import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;
import lombok.Getter;

public class WorkTimeCalculate {

    private WorkDateAndTime workTimeInputParam;

    @Getter
    private NormalWorkTimeMinutes normalWorkTimeMinutes;

    @Getter
    private OverWorkTimeMinutes overWorkTimeMinutes;

    /**
     * コンストラクタ
     *
     * @param workTimeInputParam
     */
    public WorkTimeCalculate(WorkDateAndTime workTimeInputParam) {
        this.workTimeInputParam = workTimeInputParam;
        setWorkMinutes();
        setOverWorkMinutes(this.normalWorkTimeMinutes.getValue());
    }

    /**
     * 勤務時間を取得する。
     *
     * @return
     */
    private void setWorkMinutes() {

        int startH = Integer.valueOf(workTimeInputParam.getWorkTimeStart().getValue().substring(0, 2));
        int startM = Integer.valueOf(workTimeInputParam.getWorkTimeStart().getValue().substring(2, 4));

        int endH = Integer.valueOf(workTimeInputParam.getWorkTimeEnd().getValue().substring(0, 2));
        int endM = Integer.valueOf(workTimeInputParam.getWorkTimeEnd().getValue().substring(2, 4));
        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

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

        System.out.println(">>>> " + workMinutes);

        normalWorkTimeMinutes = new NormalWorkTimeMinutes(workMinutes);
    }

    /**
     * 残業時間を取得する。
     *
     * @return
     */
    private void setOverWorkMinutes(int workMinutes) {
        int overWorkTime = Math.max(workMinutes - 8 * 60, 0);
        overWorkTimeMinutes = new OverWorkTimeMinutes(overWorkTime);
        //overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
    }
}

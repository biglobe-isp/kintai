package com.naosim.dddwork.kintai_management.domain.duty.regist;

import com.naosim.dddwork.kintai_management.domain.word.HolidayKind;
import com.naosim.dddwork.kintai_management.domain.word.RegistrationDate;
import com.naosim.dddwork.kintai_management.domain.word.WorkingEndTime;
import com.naosim.dddwork.kintai_management.domain.word.WorkingStartTime;
import com.naosim.dddwork.kintai_management.domain.word.WorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.OverWorkingTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 勤怠情報入力
 */
@AllArgsConstructor
@Getter
public class WorkingTimeDataInput {

    private RegistrationDate registrationDate;

    private WorkingStartTime workingStartTime;

    private WorkingEndTime workingEndTime;

    private HolidayKind holidayKind;

    public WorkingTime createWorkingTime() {

        // このメソッド綺麗にしたい
        int startH;
        int startM;
        int endH;
        int endM;

        if("v".equals(holidayKind.getValue())) {
            startH = 9;
            startM = 0;
            endH = 20;
            endM = 0;
        } else if("am".equals(holidayKind.getValue())) {
            startH = 9;
            startM = 0;
            endH = Integer.valueOf(workingEndTime.getValue().substring(0, 2));
            endM = Integer.valueOf(workingEndTime.getValue().substring(2, 4));

        } else if("pm".equals(holidayKind.getValue())) {
            startH = Integer.valueOf(workingStartTime.getValue().substring(0, 2));
            startM = Integer.valueOf(workingStartTime.getValue().substring(2, 4));
            endH = 20;
            endM = 0;
        } else {
            startH = Integer.valueOf(workingStartTime.getValue().substring(0, 2));
            startM = Integer.valueOf(workingStartTime.getValue().substring(2, 4));
            endH = Integer.valueOf(workingEndTime.getValue().substring(0, 2));
            endM = Integer.valueOf(workingEndTime.getValue().substring(2, 4));
        }

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if(endH == 12) {
            workMinutes -= endM;
        } else if(endH >= 13) {
            workMinutes -= 60;
        }

        // 休憩増える対応
        if(endH == 15) {
            workMinutes -= endM;
        } else if(endH >= 16) {
            workMinutes -= 60;
        }

        if(endH == 18) {
            workMinutes -= endM;
        } else if(endH >= 19) {
            workMinutes -= 60;
        }

        if(endH == 21) {
            workMinutes -= endM;
        } else if(endH >= 22) {
            workMinutes -= 60;
        }

        return new WorkingTime(workMinutes);
    }

    public OverWorkingTime createOverWoringTime() {
        return new OverWorkingTime(Math.max(this.createWorkingTime().getValue() - 8 * 60, 0));
    }
}

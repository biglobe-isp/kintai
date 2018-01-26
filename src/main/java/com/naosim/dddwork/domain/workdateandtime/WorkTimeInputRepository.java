package com.naosim.dddwork.domain.workdateandtime;

import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;

/**
 * 勤怠時間入力
 */
public interface WorkTimeInputRepository {
    public void registerWork_time(WorkDateAndTime WorkDateAndTime, WorkTimeMinutes workTimeMinutes);
}

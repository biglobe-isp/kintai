package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;

/**
 * 勤務時刻のvalueオブジェクト
 */
public class WorkTime {
    /* 開始時刻 */
    @Getter
    private StartTime startTime;

    /* 終業時刻 */
    @Getter
    private EndTime endTime;

    /**
     * コンストラクタ
     * @param startTimeToString 開始時刻
     * @param endTimeToString 終業時刻
     */
    public WorkTime(String startTimeToString, String endTimeToString) throws ValidatorException {
        StartTime startTime = new StartTime(startTimeToString);
        EndTime endTime = new EndTime(endTimeToString);
        validate(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public WorkTime(StartTime startTime, EndTime endTime) throws ValidatorException {
        validate(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected void validate(StartTime startTime, EndTime endTime) throws ValidatorException {
        compareStartEnd(startTime, endTime);
    }

    /**
     * 開始時刻と終業時刻の比較。開始時刻が終業時刻より後には設定されてはいけない。
     * @throws ValidatorException 開始時刻が終業時刻より後には設定されている場合にスローされる
     */
    protected void compareStartEnd(StartTime startTime, EndTime endTime) throws ValidatorException {
        if(Integer.parseInt(startTime.getStartTime()) >= Integer.parseInt(endTime.getEndTime())) {
            throw new ValidatorException("開始時刻が終業時刻より、後に設定されています。");
        }
    }
}

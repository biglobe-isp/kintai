package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;

/**
 * 勤怠管理として勤務時刻を管理するValue Object
 * 勤怠時刻には始業時刻{@link StartTime}と終業時刻{@link EndTime}を持ちます。
 */
public class WorkTime {
    // 開始時刻
    @Getter
    private StartTime startTime;

    // 終業時刻
    @Getter
    private EndTime endTime;

    /**
     * コンストラクタ
     * 引数の開始時刻と終業時刻を組み合わせたバリデーションチェックを実施し、異常を検知した場合は{@link ValidatorException}をスローします。
     * @param startTime 始業時刻
     * @param endTime 終業時刻
     * @throws ValidatorException バリデーションチェックで異常を検知した場合にスロー
     */
    public WorkTime(StartTime startTime, EndTime endTime) throws ValidatorException {
        validate(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 引数の開始時刻と終業時刻を組み合わせたバリデーションチェックを実施します。チェックで異常を検知した場合は{@link ValidatorException}をスローします。
     * @param startTime 始業時刻
     * @param endTime 終業時刻
     * @throws ValidatorException バリデーションチェックで異常を検知した場合にスロー
     */
    protected void validate(StartTime startTime, EndTime endTime) throws ValidatorException {
        compareStartEnd(startTime, endTime);
    }

    /**
     * 開始時刻と終業時刻の比較します。
     * @throws ValidatorException 開始時刻が終業時刻より後に設定されている場合、{@link ValidatorException}をスローします。
     */
    protected void compareStartEnd(StartTime startTime, EndTime endTime) throws ValidatorException {
        if(Integer.parseInt(startTime.getStartTime()) >= Integer.parseInt(endTime.getEndTime())) {
            throw new ValidatorException("開始時刻が終業時刻より、後に設定されています。");
        }
    }
}

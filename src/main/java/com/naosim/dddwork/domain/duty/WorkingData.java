package com.naosim.dddwork.domain.duty;

import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkTime;
import com.naosim.dddwork.domain.word.WorkingMinute;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 勤務データ
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@Getter
public class WorkingData {

    /**
     * 出勤日
     */
    private WorkDate workDate;

    /**
     * 出勤時間
     */
    private WorkTime workTimeFrom;

    /**
     * 退勤時間
     */
    private WorkTime workTimeTo;

    /**
     * 勤務時間
     */
    private WorkingMinute workingMinute;

    /**
     * 残業時間
     */
    private WorkingMinute overWorkingMinute;

    /**
     * コンストラクタ（CSVデータからの変換）
     */
    public WorkingData(String str) {

        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("取り込み元のCSVファイルが不正です");
        }

        String[] strings = str.
                replaceAll("\"", "").
                replaceAll("-", "").
                replaceAll(":", "").
                split(",", -1);

        if (strings.length == 5) {
            this.workDate = new WorkDate(strings[0]);
            this.workTimeFrom = new WorkTime(strings[1]);
            this.workTimeTo = new WorkTime(strings[2]);
            this.workingMinute = new WorkingMinute(BigDecimal.valueOf(Double.valueOf(strings[3])).setScale(2, BigDecimal.ROUND_HALF_UP));
            this.overWorkingMinute = new WorkingMinute(BigDecimal.valueOf(Double.valueOf(strings[4])).setScale(2, BigDecimal.ROUND_HALF_UP));

        } else {
            throw new IllegalArgumentException("取り込み元のCSVファイルが不正です");
        }
    }

    /**
     * コンストラクタ（パラメータからの変換）
     */
    WorkingData(WorkDate workDate, WorkTime workTimeFrom, WorkTime workTimeTo, WorkingMinute convertedWorkMinute, WorkingMinute convertedOverWorkMinute) {
        this.workDate = workDate;
        this.workTimeFrom = workTimeFrom;
        this.workTimeTo = workTimeTo;
        this.workingMinute = convertedWorkMinute;
        this.overWorkingMinute = convertedOverWorkMinute;
    }
}

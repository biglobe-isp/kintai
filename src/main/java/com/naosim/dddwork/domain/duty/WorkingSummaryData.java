package com.naosim.dddwork.domain.duty;

import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkingMinute;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 勤務データサマリ
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class WorkingSummaryData {

    /**
     * 集計開始日
     */
    private WorkDate workDateStart;

    /**
     * 集計終了日
     */
    private WorkDate workDateEnd;

    /**
     * 勤務時間合計
     */
    private WorkingMinute workingMinuteSummary;

    /**
     * 残業時間合計
     */
    private WorkingMinute overWorkingMinuteSummary;

    /**
     * コンストラクタ
     */
    public WorkingSummaryData(
            WorkDate workDateStart,
            WorkDate workDateEnd,
            WorkingMinute workingMinuteSummary,
            WorkingMinute overWorkingMinuteSummary
    ) {
        this.workDateStart = workDateStart;
        this.workDateEnd = workDateEnd;
        this.workingMinuteSummary = workingMinuteSummary;
        this.overWorkingMinuteSummary = overWorkingMinuteSummary;
    }
}

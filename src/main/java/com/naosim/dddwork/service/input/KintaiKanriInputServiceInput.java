package com.naosim.dddwork.service.input;

import com.naosim.dddwork.domain.word.MethodType;
import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 勤務管理登録Service パラメータ
 */
public class KintaiKanriInputServiceInput {

    /**
     * methodType
     */
    @Getter
    @Setter
    private MethodType methodType;

    /**
     * 対象年月日
     */
    @Getter
    @Setter
    private WorkDate workDate;

    /**
     * 出勤時間
     */
    @Getter
    @Setter
    private WorkTime workTimeFrom;

    /**
     * 退勤時間
     */
    @Getter
    @Setter
    private WorkTime workTimeTo;

    public KintaiKanriInputServiceInput(
            MethodType methodType,
            WorkDate workDate,
            WorkTime workTimeFrom,
            WorkTime workTimeTo
    ) {
        this.methodType = methodType;
        this.workDate = workDate;
        this.workTimeFrom = workTimeFrom;
        this.workTimeTo = workTimeTo;
    }
}

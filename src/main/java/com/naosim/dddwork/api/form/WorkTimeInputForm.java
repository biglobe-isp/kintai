package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.workdateandtime.WorkDate;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeEnd;
import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeNow;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeStart;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 勤怠入力入力オブジェクト
 */
@RequiredArgsConstructor
public class WorkTimeInputForm implements FormToValueObject<WorkDateAndTime> {

    @Getter
    private final String date;

    @Getter
    private final String start;

    @Getter
    private final String end;

    @Getter
    private final String now;

    @Override
    public WorkDateAndTime getValueObject() {
        return new WorkDateAndTime(new WorkDate(date), new WorkTimeStart(start), new WorkTimeEnd(end), new WorkTimeNow(now));
    }
}

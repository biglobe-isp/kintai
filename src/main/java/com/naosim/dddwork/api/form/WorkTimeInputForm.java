package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.WorkTimeInputParam;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class WorkTimeInputForm implements FormToValueObject<WorkTimeInputParam> {

    @Getter
    private final String date;

    @Getter
    private final String start;

    @Getter
    private final String end;

    @Getter
    private final String now;

    @Override
    public WorkTimeInputParam getValueObject() {
        return new WorkTimeInputParam(date, start, end, now);
    }
}

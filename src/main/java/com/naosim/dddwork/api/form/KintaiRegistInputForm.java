package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.DateString;
import com.naosim.dddwork.domain.NowString;
import com.naosim.dddwork.domain.TimeString;
import com.naosim.dddwork.domain.WorkStartAndEndTimeOfOneDay;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class KintaiRegistInputForm implements FormToValueObject<WorkStartAndEndTimeOfOneDay> {

    @Getter
    private final String[] args;

    @Override
    public WorkStartAndEndTimeOfOneDay getValueObject() {

        if (!this.isEnoughArgsLength()) {
            throw new RuntimeException("引数が足りません");
        }

        return new WorkStartAndEndTimeOfOneDay(
                new DateString(args[1]), new TimeString(args[2]), new TimeString(args[3]), new NowString()
        );
    }

    private boolean isEnoughArgsLength() {
        return this.args.length >= 4;
    }
}

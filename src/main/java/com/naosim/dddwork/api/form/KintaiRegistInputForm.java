package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.kintai.WorkStartAndEndTimeOfOneDay;
import com.naosim.dddwork.domain.kintai.time.Now;
import com.naosim.dddwork.domain.kintai.time.work.WorkDate;
import com.naosim.dddwork.domain.kintai.time.work.WorkEndTime;
import com.naosim.dddwork.domain.kintai.time.work.WorkStartTime;
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
                new WorkDate(args[1]), new WorkStartTime(args[2]), new WorkEndTime(args[3]), new Now()
        );
    }

    private boolean isEnoughArgsLength() {
        return this.args.length >= 4;
    }
}

package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.WorkStartAndEndTimeOfOneDay;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

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

        return new WorkStartAndEndTimeOfOneDay(args[1], args[2], args[3], LocalDateTime.now().toString());
    }

    private boolean isEnoughArgsLength() {
        return this.args.length >= 4;
    }
}

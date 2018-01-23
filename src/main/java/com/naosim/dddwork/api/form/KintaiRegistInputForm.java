package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.KintaiRegistInput;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
public class KintaiRegistInputForm implements FormToValueObject<KintaiRegistInput> {

    @Getter
    private final String[] args;

    @Override
    public KintaiRegistInput getValueObject() {

        if(!this.isEnoughArgsLength()) {
            throw new RuntimeException("引数が足りません");
        }

        return new KintaiRegistInput(args[1], args[2], args[3], LocalDateTime.now().toString());
    }

    private boolean isEnoughArgsLength() {
        return this.args.length >= 4;
    }
}

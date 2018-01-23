package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.TotalKintaiPrintInput;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
public class TotalKintaiPrintInputForm implements FormToValueObject<TotalKintaiPrintInput> {

    @Getter
    private final String[] args;

    @Override
    public TotalKintaiPrintInput getValueObject() {

        if(!this.isEnoughArgsLength()) {
            throw new RuntimeException("引数が足りません");
        }

        return new TotalKintaiPrintInput(this.args[1]);
    }

    private boolean isEnoughArgsLength() {
        return this.args.length >= 2;
    }
}

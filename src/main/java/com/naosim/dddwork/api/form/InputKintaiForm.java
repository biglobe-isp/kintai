package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.InputKintai;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class InputKintaiForm implements FormToValueObject<InputKintai> {

    private final String[] args;

    @Override
    public InputKintai getValueObject() {
        // TODO: ドメインオブジェクトには加工済みのデータを渡すよう修正
        return new InputKintai(args);
    }
}

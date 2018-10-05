package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.attendance.EndTime;
import jp.co.biglobe.lib.publication.date.DateParser;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.Collectors;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class EndTimeForm implements FormToValueObject<EndTime> {

    @Getter
    @NotNull
    private String value;

    public EndTimeForm(String[] args) {

        // 仕様変更 入力方法変更
        // java Main -date:20170101 -start:0900 -end:1800
        // 「-end:」が指定されている場合に、end要素を抽出する。
        if (isInputAttributeEndAssigned(args)) {

            this.value = Arrays.stream(args)
                    .filter(arg -> arg.startsWith("-end:"))
                    .map(arg ->
                            arg.replace("-end:", "")
                    )
                    .collect(Collectors.toList())
                    .get(0) + "00";

            return;
        }

        throw new RuntimeException("引数が足りません");
    }

    private boolean isInputAttributeEndAssigned(String[] args) {

        return Arrays.stream(args)
                .anyMatch(arg ->
                        arg.startsWith("-end:")
                );
    }

    @Override
    public EndTime getValueObject() {
        return new EndTime(DateParser.parse_HHmmss(this.getValue()));
    }
}

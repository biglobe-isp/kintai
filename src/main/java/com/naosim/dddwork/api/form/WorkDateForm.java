package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.attendance.WorkDate;
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
public class WorkDateForm implements FormToValueObject<WorkDate> {

    @Getter
    @NotNull
    private String value;

    public WorkDateForm(String[] args) {

        // 仕様変更 入力方法変更
        // java Main -date:20170101 -start:0900 -end:1800
        // 「-date:」が指定されている場合に、date要素を抽出する。
        if (isInputAttributeDateAssigned(args)) {

            this.value = Arrays.stream(args)
                    .filter(arg -> arg.startsWith("-date:"))
                    .map(arg ->
                            arg.replace("-date:", "")
                    )
                    .collect(Collectors.toList())
                    .get(0);

            return;
        }

        throw new RuntimeException("引数が足りません");
    }

    private boolean isInputAttributeDateAssigned(String[] args) {

        return Arrays.stream(args)
                .anyMatch(arg ->
                        arg.startsWith("-date:")
                );
    }

    @Override
    public WorkDate getValueObject() {
        return new WorkDate(DateParser.parse_yyyyMMdd(this.getValue()));
    }
}

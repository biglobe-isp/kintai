package com.naosim.dddwork.api.form;

import com.naosim.dddwork.api.attendance.MethodType;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class MethodTypeForm implements FormToValueObject<MethodType> {

    @Getter
    @NotNull
    private String value;

    public MethodTypeForm(String[] args) {

        if (this.isInputError(args)) {
            throw new RuntimeException("引数が足りません");
        }

        // 20181002 仕様変更 MOD START
        // 仕様変更 入力方法変更
        // java Main -date:20170101 -start:0900 -end:1800
        // 「-date:」「-start:」「-end:」が指定されている場合は、
        // "input"が入力されているものと見做す。
        if (this.isInputAttributesAllAssigned(args)) {

            this.value = "input";

            return;
        }
        // 20181002 仕様変更 MOD END

        this.value = args[0];
    }

    // 20181002 仕様変更 ADD START
    private boolean isInputError(String[] args) {

        return args == null || args.length < 1;
    }

    private boolean isInputAttributesAllAssigned(String[] args) {

        return this.isNotInput(args) &&
                this.isInputAttributeDateAssigned(args) &&
                this.isInputAttributeStartAssigned(args) &&
                this.isInputAttributeEndAssigned(args);
    }

    private boolean isNotInput(String[] args) {

        return !"input".equals(args[0]);
    }

    private boolean isInputAttributeDateAssigned(String[] args) {

        return Arrays.stream(args)
                .anyMatch(arg ->
                        arg.startsWith("-date:")
                );
    }

    private boolean isInputAttributeStartAssigned(String[] args) {

        return Arrays.stream(args)
                .anyMatch(arg ->
                        arg.startsWith("-start:")
                );
    }

    private boolean isInputAttributeEndAssigned(String[] args) {

        return Arrays.stream(args)
                .anyMatch(arg ->
                        arg.startsWith("-end:")
                );
    }
    // 20181002 仕様変更 ADD END

    @Override
    public MethodType getValueObject() {
        return new MethodType(this.getValue());
    }
}

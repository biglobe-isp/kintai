package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.WorkDate;
import jp.co.biglobe.lib.publication.validation.FormatDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.ValidationException;


/**
 * 出勤日Form
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkDateForm {

    @Getter
    @FormatDate
    private String value;

    public WorkDateForm(String value) {

        // [-date:]を取り除いてからセット
        if (value != null) {
            String[] tmpStr = value.split(":", -1);
            if (tmpStr.length > 1) {
                value = tmpStr[1];
            }

            // TODO -date:がついてないとValidationエラー、とかできないだろうか？
            // TODO form以外でチェックするとCSVに出力済みの値とかも関係してくるのでここがいい（はず）
            // TODO Validation用のアノテーションもここにつけることだし
            if (!"-date".equals(tmpStr[0])) {
                throw new ValidationException("指定された形式で入力してください：出勤日");
            }
        }
        this.value = value;
    }

    public WorkDate getValueObject() {
        return new WorkDate(value);
    }
}

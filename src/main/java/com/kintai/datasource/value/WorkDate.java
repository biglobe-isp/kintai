package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * 勤怠管理として勤務日を管理するValue Object
 */
public class WorkDate {
    // 終業時刻の値
    @Getter
    protected String workDate;

    /**
     * コンストラクタ
     * 引数の勤務日のバリデーションチェックを実施し、異常を検知した場合は{@link ValidatorException}をスローします。
     * @param workDate 勤務日
     * @throws ValidatorException 異常を検知した場合にスロー
     */
    public WorkDate(String workDate) throws ValidatorException {
        validate(workDate);
        this.workDate = workDate;
    }

    /**
     * 勤務日のバリデーションチェックを実施します。チェックで異常を検知した場合は{@link ValidatorException}をスローします。
     * @param workDate 勤務日
     * @throws ValidatorException バリデーションチェックで異常を検知した場合にスロー
     */
    protected void validate(String workDate) throws ValidatorException {
        isRequired(workDate);
        isDate(workDate);
    }

    /**
     * 勤務日の必須チェックをします。
     * @param workDate 勤務日
     * @throws ValidatorException 勤務日がnullや空文字。スペースのみ(半角、全角両方)の場合、{@link ValidatorException}をスローします。
     */
    protected void isRequired(String workDate) throws ValidatorException {
        if(!StringUtils.hasText(workDate)) {
            throw new ValidatorException("勤務日は必須です。");
        }
    }

    /**
     * 日付の形式チェックをします。
     * 日付の形式が「uuuuMMdd」であるかチェックします。
     * @param workDate 勤務日
     * @throws ValidatorException 指定した日付の形式ではない場合、{@link ValidatorException}をスローします。
     */
    protected void isDate(String workDate) throws ValidatorException {
        try {
            /*
             * 勤務日はyyyyMMddであるかチェック。※DateTimeFormatterでは、西暦はyyyyではなくuuuu
             * 厳密にチェックするため、ResolverStyle.STRICTを設定
             */
            LocalDate.parse(workDate, DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new ValidatorException("勤務日の形式はyyyyMMddでなければなりません。");
        }
    }
}

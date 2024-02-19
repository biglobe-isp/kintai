package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * 勤務日のvalueオブジェクト
 */
public class WorkDate {
    /* 勤務日 */
    @Getter
    protected String workDate;

    /**
     * コンストラクタ
     * @param workDate 勤務日
     */
    public WorkDate(String workDate) throws ValidatorException {
        validate(workDate);
        isDate(workDate);
        this.workDate = workDate;
    }

    /**
     * バリデーションチェック
     * 必須チェック、日付形式チェック
     * @throws ValidatorException
     */
    protected void validate(String workDate) throws ValidatorException {
        if (!StringUtils.hasText(workDate)) {
            throw new ValidatorException("勤務日は必須です。");
        }
    }

    /**
     * 日付形式チェック
     * @return 形式チェック結果
     * @throws ValidatorException 存在しない日付または指定した形式(uuuuMMdd)以外の場合にスローされる
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

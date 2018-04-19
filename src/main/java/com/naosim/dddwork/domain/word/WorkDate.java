package com.naosim.dddwork.domain.word;

import jp.co.biglobe.lib.publication.date.DateParser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 勤務時間（年月日）
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkDate {

    /**
     * 入力値
     */
    @Getter
    private final LocalDate value;

    /**
     * コンストラクタ
     */
    public WorkDate(String str) {
        if (str != null) {
            // yyyyMMddでフォーマットしたLocalDateを返す
            value = DateParser.parse_yyyyMMdd(str);
        } else {
            value = null;
        }
    }

    /**
     * LocalDate型で返却
     */
    public LocalDate convertLocalDate() {
        return this.value;
    }
}

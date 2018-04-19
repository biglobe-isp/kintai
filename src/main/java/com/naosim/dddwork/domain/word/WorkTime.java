package com.naosim.dddwork.domain.word;

import jp.co.biglobe.lib.publication.date.DateParser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * 出勤時間、退勤時間（時分）
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkTime {

    /**
     * 入力値
     */
    @Getter
    private final LocalTime value;

    /**
     * コンストラクタ
     */
    public WorkTime(String str) {
        if (str != null) {
            // HHmmssでフォーマットしたLocalTimeを返す
            this.value = DateParser.parse_HHmmss(str + "00");
        } else {
            this.value = null;
        }
    }

    /**
     * 時を取得
     */
    public WorkingHour getHour() {
        return new WorkingHour(BigDecimal.valueOf(this.value.getHour()));
    }

    /**
     * 分を取得
     */
    public WorkingMinute getMinute() {
        return new WorkingMinute(BigDecimal.valueOf(this.value.getMinute()));
    }

    /**
     * LocalTime型で返却
     */
    public LocalTime convertLocalTime() {
        return this.value;
    }
}
